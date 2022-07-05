package com.icapps.summerschool.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(private var toDoList: MutableList<String>) : RecyclerView.Adapter<TodoItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.populateViewHolder(toDoList[position], position)
    }

    override fun getItemCount(): Int {
        return toDoList.count()
    }

    fun getTodoList(): MutableList<String> {
        return toDoList
    }

    fun setTodoList(newTodoList: MutableList<String>) {
        toDoList = newTodoList
    }

    fun addNewTodoItem(todoItem: String) {
        if (todoItem.isNotEmpty())
            toDoList.add(todoItem)
    }
}