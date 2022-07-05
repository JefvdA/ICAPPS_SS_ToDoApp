package com.icapps.summerschool.todoapp

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LaunchActivity : AppCompatActivity() {

    private val listDataManager: ListDataManager = ListDataManager(this)

    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var addTodoItemFAB: FloatingActionButton

    private lateinit var todoListAdapter: TodoListAdapter

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        todoListRecyclerView = findViewById<RecyclerView?>(R.id.todoListRecyclerView).let {
            it.layoutManager = LinearLayoutManager(this@LaunchActivity)
            todoListAdapter = TodoListAdapter(listDataManager.readTodoList())
            it.adapter = todoListAdapter
            it
        }

        addTodoItemFAB = findViewById(R.id.addTodoItemFAB)
        addTodoItemFAB.setOnClickListener {
            showCreateTodoItemDialog()
        }
    }

    override fun onStart() {
        val todoList = listDataManager.readTodoList()
        todoListAdapter.setTodoList(todoList)

        super.onStart()
    }

    override fun onStop() {
        listDataManager.saveTodoList(todoListAdapter.getTodoList())

        super.onStop()
    }

    private fun showCreateTodoItemDialog() {
        val addTodoItemEditText = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT
        }

        AlertDialog.Builder(this)
            .setTitle("Create todo item")
            .setMessage("Give the description of the todo item:")
            .setView(addTodoItemEditText)
            .setPositiveButton("Create") { dialogInterface: DialogInterface, _: Int ->
                val newTodoItemDescription = addTodoItemEditText.text.toString()
                todoListAdapter.addNewTodoItem(TodoItem(newTodoItemDescription, mutableListOf()))
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }
}
