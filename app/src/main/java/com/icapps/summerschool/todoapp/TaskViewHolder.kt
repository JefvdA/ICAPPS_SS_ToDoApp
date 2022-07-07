package com.icapps.summerschool.todoapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder(taskView: View): RecyclerView.ViewHolder(taskView) {

    private val listDescriptionTextView: TextView = itemView.findViewById(R.id.list_item_todo_description)
    private val listNumberTextView: TextView = itemView.findViewById(R.id.list_item_todo_number)

    fun populateViewHolder(taskDescription: String, position: Int) {
        listNumberTextView.text = itemView.resources.getString(R.string.list_item_todo_number, position + 1)
        listDescriptionTextView.text = taskDescription
    }
}
