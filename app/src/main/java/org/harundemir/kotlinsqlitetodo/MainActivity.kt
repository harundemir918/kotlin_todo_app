package org.harundemir.kotlinsqlitetodo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoList = ArrayList<TodoModel>()

        todoList.add(TodoModel("First todo", "09:00", true))
        todoList.add(TodoModel("Second todo", "10:00", true))
        todoList.add(TodoModel("Third todo", "11:00", false))
        todoList.add(TodoModel("Fourth todo", "12:00", false))
        todoList.add(TodoModel("Fifth todo", "13:00", false))

        todoListview.adapter = TodoAdapter(applicationContext, R.layout.todo_row, todoList)
    }

    fun addTodo(view: View) {
        AddTodoBottomSheet().apply {
            show(supportFragmentManager, AddTodoBottomSheet.TAG)
        }
    }
}