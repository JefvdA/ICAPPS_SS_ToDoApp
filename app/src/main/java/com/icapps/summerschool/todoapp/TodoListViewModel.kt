package com.icapps.summerschool.todoapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.preference.PreferenceManager

class ListDataManager(application: Application): AndroidViewModel(application) {

    private val context = application.applicationContext

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
