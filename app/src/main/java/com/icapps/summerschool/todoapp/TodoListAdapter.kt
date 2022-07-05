package com.icapps.summerschool.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(private var toDoList: MutableList<TodoItem>) : RecyclerView.Adapter<TodoItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.populateViewHolder(toDoList[position].description, position)
    }

    override fun getItemCount(): Int {
        return toDoList.count()
    }

    fun getTodoList(): MutableList<TodoItem> {
        return toDoList
    }

    fun setTodoList(newTodoList: MutableList<TodoItem>) {
        toDoList = newTodoList
    }

    fun addNewTodoItem(todoItem: TodoItem) {
        if (todoItem.description.isNotEmpty())
            toDoList.add(todoItem)
    }
}