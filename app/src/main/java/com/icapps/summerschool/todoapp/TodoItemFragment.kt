package com.icapps.summerschool.todoapp
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        addTaskFAB = binding.addTaskFAB

        (activity as AppCompatActivity).supportActionBar?.title = todoItem.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}