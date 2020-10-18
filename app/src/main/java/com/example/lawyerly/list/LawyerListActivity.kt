package com.example.lawyerly.list

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyerly.R
import com.example.lawyerly.detail.LawyerDetailActivity
import com.example.lawyerly.model.Lawyer
import com.example.lawyerly.model.LawyerRepository
import kotlinx.android.synthetic.main.activity_lawyer_list.*
import kotlinx.android.synthetic.main.lawyer_list_toolbar_content.*

class LawyerListActivity : AppCompatActivity() {

    val lawyerRepository = LawyerRepository()
    val adapter = LawyerListAdapter()

    val parallaxListener = object: RecyclerView.OnScrollListener() {
        var scrollOffset = 0
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            scrollOffset += dy
            if (scrollOffset < 0) {
                scrollOffset = 0
            }
            parallax_image.translationY = - (scrollOffset * 0.65f)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer_list)

        lawyer_list.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        lawyer_list.adapter = adapter
        adapter.setItems(lawyerRepository.lawyers)
        adapter.listener = object: LawyerListAdapter.Listener {
            override fun itemClicked(item: Lawyer) {
                startActivity(LawyerDetailActivity.getIntent(this@LawyerListActivity, item))
            }
        }

        addTab(R.string.upper_tab_1_title)
        addTab(R.string.upper_tab_2_title)
        addTab(R.string.upper_tab_3_title)
    }

    fun addTab(@StringRes title: Int) {
        tabs.addTab(
            tabs.newTab().apply {
                text = getString(title)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        lawyer_list.addOnScrollListener(parallaxListener)
    }

    override fun onPause() {
        lawyer_list.removeOnScrollListener(parallaxListener)
        super.onPause()
    }

}