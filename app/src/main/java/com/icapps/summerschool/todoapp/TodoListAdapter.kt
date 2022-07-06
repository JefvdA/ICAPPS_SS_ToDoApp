package com.icapps.summerschool.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(var todoList: MutableList<TodoItem>, private val clickListener: ITodoClickListener) : RecyclerView.Adapter<TodoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_todo, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.populateViewHolder(todoList[position], position)
    }

    override fun getItemCount(): Int {
        return todoList.count()
    }

    fun addNewTodoItem(todoItem: TodoItem) {
        if (todoItem.description.isNotEmpty()) {
            todoList.add(todoItem)
            this.notifyItemInserted(todoList.size - 1)
        }
        sortTodoList()
        notifyItemRangeChanged(todoList.indexOf(todoItem), todoList.size)
    }

    fun sortTodoList() {
        val sortedTodoList = todoList.sortedBy { it.description.lowercase() }.toMutableList()
        todoList = sortedTodoList
    }
}