package org.harundemir.kotlinsqlitetodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.LENGTH_SHORT

class TodoAdapter(var todoContext: Context, var resources: Int, var todos: List<TodoModel>) :
    ArrayAdapter<TodoModel>(todoContext, resources, todos) {
    private val dbHelper = DbHelper(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(todoContext)
        val view: View = layoutInflater.inflate(resources, null)

        val todoText: TextView = view.findViewById(R.id.todoText)
        val hourText: TextView = view.findViewById(R.id.hourText)
        val isDoneCheck: CheckBox = view.findViewById(R.id.isDoneCheck)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)

        val todo: TodoModel = todos[position]
        todoText.text = todo.title
        hourText.text = todo.hour
        isDoneCheck.isChecked = if (todo.isDone == 1) true else (false)

        deleteButton.setOnClickListener(View.OnClickListener {
            dbHelper.delete(title = todoText.text.toString(), hour = hourText.text.toString())
            Toast.makeText(context, "${todoText.text.toString()} deleted.", LENGTH_SHORT).show()
        })

        return view
    }
}