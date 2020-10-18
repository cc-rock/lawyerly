package com.example.lawyerly.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyerly.R
import com.example.lawyerly.model.Lawyer

class LawyerListAdapter: RecyclerView.Adapter<LawyerListAdapter.LawyerViewHolder>() {

    private val items: MutableList<Lawyer> = mutableListOf()

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LawyerViewHolder {
        return LawyerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.lawyer_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LawyerViewHolder, position: Int) {
        val item = items[position]
        holder.photo.setImageDrawable(
            ResourcesCompat.getDrawable(holder.itemView.resources, item.image, null)
        )
        holder.name.text = holder.itemView.resources.getString(
            R.string.lawyer_name_format, item.firstName, item.lastName
        )
        holder.speciality.text = item.speciality
        holder.rate.text = holder.itemView.resources.getString(
            R.string.hourly_rate_format, item.hourlyRate
        )
        holder.rating.text = holder.itemView.resources.getString(
            R.string.rating_format, item.rating
        )
        holder.verified.visibility = if (item.verified) View.VISIBLE else View.GONE
        holder.cardView.setOnClickListener { listener?.itemClicked(item) }
    }

    fun setItems(newItems: List<Lawyer>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class LawyerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photo)
        val name: TextView = itemView.findViewById(R.id.name)
        val speciality: TextView = itemView.findViewById(R.id.speciality)
        val rate: TextView = itemView.findViewById(R.id.rate)
        val rating: TextView = itemView.findViewById(R.id.rating)
        val verified: View = itemView.findViewById(R.id.verified)
        val cardView: View = itemView.findViewById(R.id.card_view)
    }

    interface Listener {
        fun itemClicked(item: Lawyer)
    }
}