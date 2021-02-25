package org.harundemir.kotlinsqlitetodo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DbHelper(this)

        val todoList: ArrayList<TodoModel> = dbHelper.getAll()
        val todoAdapter = TodoAdapter(applicationContext, R.layout.todo_row, todoList)
        todoListview.adapter = todoAdapter
    }

    fun addTodo(view: View) {
        AddTodoBottomSheet().apply {
            show(supportFragmentManager, AddTodoBottomSheet.TAG)
        }
    }
}