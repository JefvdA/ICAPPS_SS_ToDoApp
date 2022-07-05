package com.icapps.summerschool.todoapp

import android.content.res.Resources
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val listDescriptionTextView: TextView = itemView.findViewById(R.id.list_item_todo_description)
    private val listNumberTextView: TextView = itemView.findViewById(R.id.list_item_todo_number)

    fun populateViewHolder(description: String, position: Int) {
        listNumberTextView.text = itemView.resources.getString(R.string.list_item_todo_number, position + 1)
        listDescriptionTextView.text = description
    }
}
