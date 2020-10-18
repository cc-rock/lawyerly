package com.example.lawyerly.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyerly.R
import com.example.lawyerly.model.Lawyer
import kotlinx.android.synthetic.main.activity_lawyer_detail.*
import kotlinx.android.synthetic.main.lawyer_list_item.*

class LawyerDetailActivity : AppCompatActivity() {

    lateinit var lawyer: Lawyer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer_detail)
        lawyer = requireNotNull(intent.getParcelableExtra(EXTRA_LAWYER))
        back.setOnClickListener { finish() }
        photo.setImageDrawable(
            ResourcesCompat.getDrawable(resources, lawyer.image, null)
        )
        name.text = getString(
            R.string.lawyer_name_format, lawyer.firstName, lawyer.lastName
        )
        speciality.text = lawyer.speciality
        rate.text = resources.getString(
            R.string.hourly_rate_format, lawyer.hourlyRate
        )
        rating.text = resources.getString(
            R.string.rating_format, lawyer.rating
        )
        verified.visibility = if (lawyer.verified) View.VISIBLE else View.GONE
        description.text = lawyer.description
        other_info.text = lawyer.otherInfo
    }

    companion object {
        private const val EXTRA_LAWYER = "EXTRA_LAWYER"

        fun getIntent(context: Context, lawyer: Lawyer): Intent {
            return Intent(context, LawyerDetailActivity::class.java).apply {
                putExtra(EXTRA_LAWYER, lawyer)
            }
        }
    }
}