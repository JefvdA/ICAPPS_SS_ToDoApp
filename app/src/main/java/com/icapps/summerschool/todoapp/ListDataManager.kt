package com.icapps.summerschool.todoapp

import android.content.Context
import androidx.preference.PreferenceManager

class ListDataManager(var context: Context) {
    fun saveTodoList(todoList: MutableList<TodoItem>) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            .edit()

        for(todoItem: TodoItem in todoList) {
            sharedPrefs
                .putStringSet(todoItem.description, todoItem.tasks.toHashSet())
        }

        sharedPrefs
            .apply()
    }

    fun readTodoList(): MutableList<TodoItem> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all

        val thingsTodo = mutableListOf<TodoItem>()

        for (todoItemDescription in contents.keys) {
            val todoTasks = sharedPrefs.getStringSet(todoItemDescription, HashSet<String>())
                ?.toMutableList() ?: mutableListOf()
            thingsTodo.add(TodoItem(todoItemDescription, todoTasks))
        }

        return thingsTodo
    }
}
