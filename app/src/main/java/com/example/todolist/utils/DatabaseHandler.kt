package com.example.todolist.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todolist.model.ToDoModel

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val VERSION = 1
        private const val NAME = "toDoListDatabase"
        private const val TODO_TABLE = "todo"
        private const val ID = "id"
        private const val TASK = "task"
        private const val STATUS = "status"
        private const val CREATE_TODO_TABLE =
            "CREATE TABLE $TODO_TABLE ($ID INTEGER PRIMARY KEY AUTOINCREMENT, $TASK TEXT, $STATUS INTEGER)"
    }

    private var db: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TODO_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TODO_TABLE")
        onCreate(db)
    }

    fun openDatabase() {
        db = writableDatabase
    }

    fun insertTask(task: ToDoModel) {
        val values = ContentValues().apply {
            put(TASK, task.task)
            put(STATUS, 0) // Assuming default status is 0
        }
        db?.insert(TODO_TABLE, null, values)
    }

    @SuppressLint("Range")
    fun getAllTasks(): List<ToDoModel> {
        val taskList = mutableListOf<ToDoModel>()
        val cursor = db?.query(
            TODO_TABLE, null, null, null, null, null, null, null
        )
        cursor?.use {
            if (cursor.moveToFirst()) {
                do {
                    val task = ToDoModel().apply {
                        id = cursor.getInt(cursor.getColumnIndex(ID))
                        this.task = cursor.getString(cursor.getColumnIndex(TASK))
                        status = cursor.getInt(cursor.getColumnIndex(STATUS))
                    }
                    taskList.add(task)
                } while (cursor.moveToNext())
            }
        }
        return taskList
    }

    fun updateStatus(id: Int, status: Int) {
        val values = ContentValues().apply {
            put(STATUS, status)
        }
        db?.update(TODO_TABLE, values, "$ID = ?", arrayOf(id.toString()))
    }

    fun updateTask(id: Int, task: String) {
        val values = ContentValues().apply {
            put(TASK, task)
        }
        db?.update(TODO_TABLE, values, "$ID=?", arrayOf(id.toString()))
    }

    fun deleteTask(id: Int) {
        db?.delete(TODO_TABLE, "$ID = ?", arrayOf(id.toString()))
    }
}



