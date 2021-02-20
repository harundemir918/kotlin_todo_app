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
        val todoAdapter = TodoAdapter(applicationContext, R.layout.todo_row, todoList)
        todoListview.adapter = todoAdapter

        try {
            val todoDatabase = openOrCreateDatabase("todos", MODE_PRIVATE, null)
            todoDatabase.execSQL("CREATE TABLE IF NOT EXISTS todos (title VARCHAR, hour TIME, isDone TINYINT)")
            val cursor = todoDatabase.rawQuery("SELECT * FROM todos", null)

            val titleIx = cursor.getColumnIndex("title")
            val hourIx = cursor.getColumnIndex("hour")
            val isDoneIx = cursor.getColumnIndex("isDone")

            cursor.moveToFirst()

            while (cursor != null) {
                todoList.add(
                    TodoModel(
                        cursor.getString(titleIx),
                        cursor.getString(hourIx),
                        cursor.getInt(isDoneIx)
                    )
                )
                todoAdapter.notifyDataSetChanged()

                cursor.moveToNext()
            }

            cursor?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addTodo(view: View) {
        AddTodoBottomSheet().apply {
            show(supportFragmentManager, AddTodoBottomSheet.TAG)
        }
    }
}