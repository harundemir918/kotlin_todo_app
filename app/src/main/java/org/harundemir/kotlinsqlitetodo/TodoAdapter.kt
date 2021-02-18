package org.harundemir.kotlinsqlitetodo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView

class TodoAdapter (var todoContext : Context, var resources : Int, var todos : List<TodoModel>) : ArrayAdapter<TodoModel>(todoContext, resources, todos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(todoContext)
        val view: View = layoutInflater.inflate(resources, null)

        val todoText: TextView = view.findViewById(R.id.todoText)
        val hourText: TextView = view.findViewById(R.id.hourText)
        val isDoneCheck: CheckBox = view.findViewById(R.id.isDoneCheck)

        val todo : TodoModel = todos[position]
        todoText.text = todo.title
        hourText.text = todo.hour
        isDoneCheck.isChecked = todo.isDone

        return view
    }
}