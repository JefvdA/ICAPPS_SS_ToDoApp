package com.icapps.summerschool.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {

    private lateinit var taskListRecyclerView: RecyclerView
    private lateinit var addTodoFAB: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        taskListRecyclerView = findViewById(R.id.todoListRecyclerView)
        addTodoFAB = findViewById(R.id.addTodoItemFAB)
        addTodoFAB.setOnClickListener {
            showCreateTaskDialog()
        }
    }

    private fun showCreateTaskDialog() {
        val addTodoItemEditText = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        }

        AlertDialog.Builder(this)
            .setTitle("Create todo item")
            .setMessage("Give the description of the todo item:")
            .setView(addTodoItemEditText)
            .setPositiveButton("Create") { dialogInterface: DialogInterface, _: Int ->
                val newTodoItemDescription = addTodoItemEditText.text.toString()
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }
}