package org.harundemir.kotlinsqlitetodo

import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_add_todo_bottom_sheet.*
import java.util.*

class AddTodoBottomSheet : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "AddTodoBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hourPickerButton.setOnClickListener(View.OnClickListener {
            val calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                hourEditText.setText(
                    SimpleDateFormat(
                        "HH:mm",
                        Locale.getDefault()
                    ).format(calendar.time)
                )
            }
            TimePickerDialog(
                context,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        })

        addButton.setOnClickListener(View.OnClickListener {
            val dbHelper: DbHelper = DbHelper(this.context)
            try {
                dbHelper.add(titleEditText.text.toString(), hourEditText.text.toString())
                val manager = requireActivity().supportFragmentManager
                manager.beginTransaction().remove(this).commit()
            } catch (e: Exception) {
                println("Error")
                e.printStackTrace()
            }
        })
    }
}