package com.example.movie.onetracktest.ui.main

import android.content.Context
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.movie.onetracktest.R

interface SetTargetDialogCallback{
    fun call(newGoal: Int)
}

fun showSetTargetDialog(context: Context, callback: SetTargetDialogCallback){
    val builder = AlertDialog.Builder(context)
    builder.setTitle(R.string.set_target)

    val input = EditText(context)
    input.inputType = InputType.TYPE_CLASS_NUMBER
    builder.setView(input)

    builder.setPositiveButton("OK") { dialog, which ->
        val newGoal =  input.text.toString().toInt()
        callback.call(newGoal)
    }
    builder.setNegativeButton("Cancel") {dialog, which ->
        dialog.cancel()
    }

    builder.show()
}