package com.icapps.summerschool.todoapp

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.icapps.summerschool.todoapp.databinding.FragmentTodoListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null

    private lateinit var todoListViewModel: TodoListViewModel

    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var addTodoItemFAB: FloatingActionButton

    private lateinit var todoListAdapter: TodoListAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        private val TAG = TodoListFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todoListViewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)
        todoListViewModel.todoList.observe(this) {
            it?.let {
                todoListAdapter.todoList = it
                todoListAdapter.notifyDataSetChanged()
            }
        }

        todoListViewModel.readTodoList()
    }

    override fun onStart() {
        super.onStart()

        todoListViewModel.readTodoList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoListRecyclerView = binding.todoListRecyclerView.let {
            it.layoutManager = LinearLayoutManager(binding.root.context)
            todoListAdapter = TodoListAdapter(mutableListOf()) { todoItem ->
                Log.d(TAG, "You have clicked on ${todoItem.description}")
                view.findNavController().navigate(R.id.action_TodoListFragment_to_TodoItemFragment)
            }
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
                todoListViewModel.saveTodoItem(TodoItem(newTodoItemDescription, mutableListOf()))
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