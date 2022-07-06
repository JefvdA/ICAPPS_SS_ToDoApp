package com.icapps.summerschool.todoapp

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.icapps.summerschool.todoapp.databinding.FragmentTodoListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null

    private lateinit var listDataManager: ListDataManager

    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var addTodoItemFAB: FloatingActionButton

    private lateinit var todoListAdapter: TodoListAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listDataManager = ListDataManager(view.context)

        todoListRecyclerView = binding.todoListRecyclerView.let {
            it.layoutManager = LinearLayoutManager(binding.root.context)
            todoListAdapter = TodoListAdapter(mutableListOf()) {}
            it.adapter = todoListAdapter
            it
        }

        addTodoItemFAB = binding.addTodoItemFAB
        addTodoItemFAB.setOnClickListener {
            showCreateTodoItemDialog()
        }
    }

    private fun showCreateTodoItemDialog() {
        val addTodoItemEditText = EditText(binding.root.context).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        }

        AlertDialog.Builder(binding.root.context)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}