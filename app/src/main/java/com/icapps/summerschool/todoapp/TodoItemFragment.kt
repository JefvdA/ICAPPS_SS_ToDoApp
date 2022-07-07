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
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.icapps.summerschool.todoapp.databinding.FragmentTodoItemBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TodoItemFragment : Fragment() {

    private var _binding: FragmentTodoItemBinding? = null
    private val args: TodoItemFragmentArgs by navArgs()

    private lateinit var taskListAdapter: TaskListAdapter

    private lateinit var taskListRecyclerView: RecyclerView
    private lateinit var addTaskFAB: FloatingActionButton

    private lateinit var todoItem: TodoItem

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoItemBinding.inflate(inflater, container, false)
        todoItem = args.todoItem

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskListRecyclerView = binding.taskListRecyclerView.let {
            it.layoutManager = LinearLayoutManager(binding.root.context)
            taskListAdapter = TaskListAdapter(mutableListOf("Test1", "Test2"))
            it.adapter = taskListAdapter
            it
        }
        addTaskFAB = binding.addTaskFAB.let {
            it.setOnClickListener {
                showAddTaskDialog()
            }
            it
        }

        (activity as AppCompatActivity).supportActionBar?.title = todoItem.description
    }

    private fun showAddTaskDialog() {
        val addTaskEditText = EditText(binding.root.context).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        }

        AlertDialog.Builder(binding.root.context)
            .setTitle(getString(R.string.create_todo_item_task_dialog_title, todoItem.description))
            .setMessage(getString(R.string.create_todo_item_dialog_message))
            .setView(addTaskEditText)
            .setPositiveButton(getString(R.string.create)) { dialogInterface: DialogInterface, _: Int ->
                val newTodoItemDescription = addTaskEditText.text.toString()
                taskListAdapter.taskList.add(newTodoItemDescription)
                taskListAdapter.notifyDataSetChanged()
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