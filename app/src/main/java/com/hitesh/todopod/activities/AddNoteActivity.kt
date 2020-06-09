package com.hitesh.todopod.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.hitesh.todopod.helper.DatabaseHelper
import com.hitesh.todopod.R

class AddNoteActivity : AppCompatActivity() {
    var editText: EditText? = null
    var saveButton: Button? = null
    var userInput: String? = null
    var db: DatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        editText = findViewById<View>(R.id.editText) as EditText
        saveButton = findViewById<View>(R.id.saveButton) as Button
        //db = DatabaseHelper(this)
    }

    fun saveNote(view: View?) {
        userInput = editText!!.text.toString()
        db!!.insertData(userInput)
        val mainActivity = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivity)
    }
}