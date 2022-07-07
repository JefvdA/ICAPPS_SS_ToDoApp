package com.icapps.summerschool.todoapp

fun interface TodoClickListener {
    fun todoItemClicked(todoItem: TodoItem)
}