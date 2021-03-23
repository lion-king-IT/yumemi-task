package com.reo.running.yumemitask.screen.details

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.R
import com.reo.running.yumemitask.databinding.DetailsviewItemRecyclerviewBinding

class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val detailsText = itemView.findViewById<TextView>(R.id.detailsText)
}