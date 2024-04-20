package com.example.todolist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapter.ToDoAdapter
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.ToDoModel

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var taskAdapter : ToDoAdapter
    private lateinit var taskList: List<ToDoModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)

        taskAdapter = ToDoAdapter(this)
        binding.tasksRecyclerView.adapter = taskAdapter

        val taskList = mutableListOf<ToDoModel>()

        val task1 = ToDoModel(1, 0, "This is a task")  // Create tasks with desired values
        val task2 = ToDoModel(2, 0, "Another task")
        val task3 = ToDoModel(3, 0, "Complete this later")
        val task4 = ToDoModel(4, 0, "Don't forget!")

        taskList.addAll(listOf(task1, task2, task3, task4))

        taskAdapter.setTask(taskList)


    }
}