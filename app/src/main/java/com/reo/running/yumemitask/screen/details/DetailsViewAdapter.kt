package com.reo.running.yumemitask.screen.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.R

class DetailsViewAdapter(val list: List<String>) : RecyclerView.Adapter<DetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.detailsview_item_recyclerview, parent, false)
        return DetailsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.detailsText.text = list[position]
    }

    override fun getItemCount(): Int = list.size

}