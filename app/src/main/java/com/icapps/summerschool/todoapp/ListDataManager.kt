package com.icapps.summerschool.todoapp

import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import kotlin.reflect.typeOf

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
        // TODO: Keep order of the items
        val contents = sharedPrefs.all

        var todoList = mutableListOf<TodoItem>()

        for (todoItemDescription in contents.keys) {
            val todoTasks = sharedPrefs.getStringSet(todoItemDescription, HashSet<String>())
                ?.toMutableList() ?: mutableListOf()
            todoList.add(TodoItem(todoItemDescription, todoTasks))
        }

        return todoList
    }
}
