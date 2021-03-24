package com.reo.running.yumemitask.screen.history

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.R

class HistoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val historyName: TextView = itemView.findViewById(R.id.historyName)
}