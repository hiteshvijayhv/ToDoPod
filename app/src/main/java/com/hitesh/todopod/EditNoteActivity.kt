package com.hitesh.todopod

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditNoteActivity : AppCompatActivity() {
    var db: DatabaseHelper? = null
    var editNote: EditText? = null
    var note: String? = null
    var newNote: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        editNote = findViewById<View>(R.id.editNote) as EditText
        db = DatabaseHelper(this)
        editNote?.setText(intent.getStringExtra("text"))
        val intent = intent
        note = intent.getStringExtra("text")
        editNote?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                db?.delete(note.toString());
            }

            override fun afterTextChanged(s: Editable) {
                newNote = editNote?.text.toString()
            }
        })
    }

    override fun onBackPressed() {
        if (newNote != null) {
            db?.insertData(newNote)
        }
        val MainActivity = Intent(applicationContext, MainActivity::class.java)
        startActivity(MainActivity)
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.edit_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteNote -> {
                db?.delete(note.toString())
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}