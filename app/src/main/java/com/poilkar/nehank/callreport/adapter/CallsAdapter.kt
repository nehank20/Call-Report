package com.poilkar.nehank.callreport.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.poilkar.nehank.callreport.R
import com.poilkar.nehank.callreport.model.CallLogModel
import kotlinx.android.synthetic.main.row_calls_log_item.view.*

class CallsAdapter(val list: List<CallLogModel>): RecyclerView.Adapter<CallsAdapter.CallViewHolder>() {

    inner class CallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_calls_log_item,parent,false)
        return CallViewHolder((view))
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        val currentItem = list[position]
        holder.itemView.apply {
            tvNumber.text = currentItem.number
            tvTimestamp.text = currentItem.timestamp.toString()
        }
    }

    override fun getItemCount(): Int = list.size
}