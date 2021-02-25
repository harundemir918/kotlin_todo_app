package org.harundemir.kotlinsqlitetodo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class DbHelper(context: Context?) : SQLiteOpenHelper(context, "todos", null, 1) {
    val TABLE = "todos"

    companion object {
        const val TITLE: String = "title"
        const val HOUR: String = "hour"
        const val ISDONE: String = "isDone"
    }

    val DATABASE_CREATE =
        "CREATE TABLE IF NOT EXISTS $TABLE ($TITLE VARCHAR, $HOUR VARCHAR, $ISDONE TINYINT)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
    }

    fun add(title: String, hour: String) {
        val values = ContentValues()
        values.put(TITLE, title)
        values.put(HOUR, hour)
        values.put(ISDONE, 0)
        writableDatabase.insert(TABLE, null, values)
    }

    fun delete(title: String, hour: String) {
        writableDatabase.delete(TABLE, "title = '$title' AND hour = '$hour'", null)
    }

    fun getAll (): ArrayList<TodoModel> {
        val todoList = ArrayList<TodoModel>()

        try {
            val cursor = writableDatabase.rawQuery("SELECT * FROM $TABLE", null)

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
                cursor.moveToNext()
            }
            cursor?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return todoList
    }

}