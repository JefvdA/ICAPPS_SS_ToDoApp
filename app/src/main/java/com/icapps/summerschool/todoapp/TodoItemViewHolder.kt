package com.icapps.summerschool.todoapp

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoItemViewHolder(itemView: View, private val clickListener: ITodoClickListener): RecyclerView.ViewHolder(itemView) {

    private val listDescriptionTextView: TextView = itemView.findViewById(R.id.list_item_todo_description)
    private val listNumberTextView: TextView = itemView.findViewById(R.id.list_item_todo_number)

    fun populateViewHolder(todoItem: TodoItem, position: Int) {
        itemView.setOnClickListener {
            clickListener.todoItemClicked(todoItem)
        }

        listNumberTextView.text = itemView.resources.getString(R.string.list_item_todo_number, position + 1)
        listDescriptionTextView.text = todoItem.description
    }
}
