package com.icapps.summerschool.todoapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager

class TodoListViewModel(application: Application): AndroidViewModel(application) {

    private val context = application.applicationContext
    private val _todoList = MutableLiveData<MutableList<TodoItem>>()
    val todoList: LiveData<MutableList<TodoItem>>
        get() = _todoList

    fun saveTodoItem(todoItem: TodoItem) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            .edit()

        sharedPrefs
            .putStringSet(todoItem.description, todoItem.tasks.toHashSet())
            .apply()

        readTodoList()
    }

    fun readTodoList() {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.all

        var thingsTodo = mutableListOf<TodoItem>()

        for (todoItemDescription in contents.keys) {
            val todoTasks = sharedPrefs.getStringSet(todoItemDescription, HashSet<String>())
                ?.toMutableList() ?: mutableListOf()
            thingsTodo.add(TodoItem(todoItemDescription, todoTasks))
        }

        thingsTodo = thingsTodo.sortedBy { it.description.lowercase() }.toMutableList()

        _todoList.postValue(thingsTodo)
    }
}
