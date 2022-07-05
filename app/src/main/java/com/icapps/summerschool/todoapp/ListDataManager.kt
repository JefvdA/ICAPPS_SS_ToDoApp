package com.icapps.summerschool.todoapp

import android.content.Context
import androidx.preference.PreferenceManager

class ListDataManager(var context: Context) {
    fun saveTodoList(todoList: MutableList<String>) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putStringSet("Mocked_data", todoList.toHashSet())
            .apply()
    }

    fun readTodoList(): MutableList<String> {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getStringSet("Mocked_data", mutableSetOf())
            ?.toMutableList() ?: mutableListOf()
    }
}
