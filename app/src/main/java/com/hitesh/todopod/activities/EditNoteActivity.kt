package com.hitesh.todopod.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hitesh.todopod.ItemsViewModel
import com.hitesh.todopod.helper.DatabaseHelper
import com.hitesh.todopod.R
import com.hitesh.todopod.items

class EditNoteActivity : AppCompatActivity() {
    var editNote: EditText? = null
    var note: String? = null
    var newNote: String? = null
    var itemsViewModel: ItemsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        editNote = findViewById<View>(R.id.editNote) as EditText
        editNote?.setText(intent.getStringExtra("text"))
        val intent = intent
        note = intent.getStringExtra("text")

        itemsViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory(application))
                .get(ItemsViewModel::class.java)

        editNote?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                deleteNote()
            }

            override fun afterTextChanged(s: Editable) {
                newNote = editNote?.text.toString()
            }
        })
    }

    override fun onBackPressed() {
        if (newNote != null) {
            val editedNote = items("" + newNote, "", 0)
           itemsViewModel?.insert(editedNote)
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
                deleteNote()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote(){
        val input = items("" + note, "", 0)
        itemsViewModel?.delete(input)
    }
}