package com.reo.running.yumemitask.screen.list.recyclerview

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.R

class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val nameOfContributor: TextView = itemView.findViewById(R.id.nameContributors)
}