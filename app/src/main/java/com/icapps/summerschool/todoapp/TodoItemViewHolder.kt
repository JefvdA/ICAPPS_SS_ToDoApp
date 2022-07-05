package com.icapps.summerschool.todoapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoItemViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView) {

    private val listDescriptionTextView: TextView = itemView.findViewById<TextView>(R.id.list_item_todo_description)
    private val listNumberTextView: TextView = itemView.findViewById<TextView>(R.id.list_item_todo_number)

    public fun populateViewHolder(description: String, position: Int) {
        listNumberTextView.text = position.toString()
        listDescriptionTextView.text = description
    }
}
