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

    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var addTodoItemFAB: FloatingActionButton

    private lateinit var todoListAdapter: ToDoListAdapter

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        todoListRecyclerView = findViewById<RecyclerView?>(R.id.todoListRecyclerView).let {
            it.layoutManager = LinearLayoutManager(this@LaunchActivity)
            todoListAdapter = ToDoListAdapter(mutableListOf("Winkelen", "Kuisen", "Koken"))
            it.adapter = todoListAdapter
            it
        }

        addTodoItemFAB = findViewById(R.id.addTodoItemFAB)
        addTodoItemFAB.setOnClickListener {
            showCreateTodoItemDialog()
        }
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
                todoListAdapter.addNewTodoItem(newTodoItemDescription)
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }
}
