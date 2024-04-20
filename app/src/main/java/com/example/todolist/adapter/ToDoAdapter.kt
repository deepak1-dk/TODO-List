package com.example.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.MainActivity
import com.example.todolist.model.ToDoModel
import com.example.todolist.R

class ToDoAdapter(private val activity: MainActivity) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    private var todoList : List<ToDoModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setTask(todoList : List<ToDoModel>){
        this.todoList = todoList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoList[position]
        holder.task.text = item.task
        holder.task.isChecked = item.status != 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val task: CheckBox = itemView.findViewById(R.id.todoCheckBox)
    }

    fun updateData(newList: List<ToDoModel>) {
        todoList = newList
        notifyDataSetChanged() // Notify adapter about data change
    }

}