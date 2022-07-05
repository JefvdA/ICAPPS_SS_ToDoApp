package com.icapps.summerschool.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LaunchActivity : AppCompatActivity() {

    private lateinit var todoListRecyclerView: RecyclerView

    private lateinit var todoListAdapter: ToDoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        todoListRecyclerView = findViewById<RecyclerView?>(R.id.todoListRecyclerView).let {
            it.layoutManager = LinearLayoutManager(this@LaunchActivity)
            todoListAdapter = ToDoListAdapter(listOf("Winkelen", "Kuisen", "Koken"))
            it.adapter = todoListAdapter
            it
        }
    }
}