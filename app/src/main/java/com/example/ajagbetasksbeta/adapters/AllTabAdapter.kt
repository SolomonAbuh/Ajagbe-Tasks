package com.example.ajagbetasksbeta.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ajagbetasksbeta.R
import com.example.ajagbetasksbeta.Task
import com.google.android.material.progressindicator.CircularProgressIndicator

class AllTabAdapter(val context: Context, val taskList: List<Task>) :
    RecyclerView.Adapter<AllTabAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.all_tab_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.taskTitle.text = currentItem.taskTitle
        holder.progressIndicator.progress = currentItem.progress

        if (currentItem.checked) holder.checkBox.setImageResource(R.drawable.ic_check_green)
        else holder.checkBox.setImageResource(R.drawable.ic_check)

    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTitle = itemView.findViewById<TextView>(R.id.task_title)
        val checkBox = itemView.findViewById<ImageView>(R.id.check_box)
        val progressIndicator = itemView.findViewById<ProgressBar>(R.id.task_progress_indicator)
    }
}