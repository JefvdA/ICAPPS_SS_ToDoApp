package com.icapps.summerschool.todoapp

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
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
        private val TAG = LaunchActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        todoListRecyclerView = findViewById<RecyclerView?>(R.id.todoListRecyclerView).let {
            it.layoutManager = LinearLayoutManager(this@LaunchActivity)
            todoListAdapter = TodoListAdapter(mutableListOf()) { todoItem ->
            }
            it.adapter = todoListAdapter
            it
        }

        addTodoItemFAB = findViewById(R.id.addTodoItemFAB)
        addTodoItemFAB.setOnClickListener {
            showCreateTodoItemDialog()
        }
    }

    override fun onStart() {
        todoListAdapter.todoList = listDataManager.readTodoList()
        todoListAdapter.sortTodoList()

        super.onStart()
    }

    override fun onStop() {
        listDataManager.saveTodoList(todoListAdapter.todoList)

        super.onStop()
    }

    private fun showCreateTodoItemDialog() {
        val addTodoItemEditText = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        }

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.create_todo_item_dialog_title))
            .setMessage(getString(R.string.create_todo_item_dialog_message))
            .setView(addTodoItemEditText)
            .setPositiveButton(getString(R.string.create)) { dialogInterface: DialogInterface, _: Int ->
                val newTodoItemDescription = addTodoItemEditText.text.toString()
                todoListAdapter.addNewTodoItem(TodoItem(newTodoItemDescription, mutableListOf()))
                dialogInterface.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }
}
