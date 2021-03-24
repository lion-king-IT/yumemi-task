package com.reo.running.yumemitask.screen.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reo.running.yumemitask.R

class HistoryViewAdapter(val list : List<String>,var index: Int) : RecyclerView.Adapter<HistoryViewHolder>() {

    private lateinit var listener :  OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_item_recyclerview,parent,false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.historyName.text = list[position]
    }

    override fun getItemCount(): Int = list.size

    interface OnClickListener {
        fun onItemClick(list: List<String>,position: Int)
    }

    fun setOnItemClickListener(listener:OnClickListener) {
        this.listener = listener
    }
}