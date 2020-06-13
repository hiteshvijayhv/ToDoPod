package com.hitesh.todopod.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hitesh.todopod.R
import com.hitesh.todopod.items
import com.hitesh.todopod.model.ItemsViewModel

class EditNoteActivity : AppCompatActivity() {
    var editNote: EditText? = null
    var note: String? = null
    var newNote: String? = null
    var statsButton: ImageButton? = null

    var itemsViewModel: ItemsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        editNote = findViewById<View>(R.id.editNote) as EditText
        statsButton = findViewById(R.id.statsButton)

        var intent: Intent = intent
        note = intent.getStringExtra("keytitle")
        editNote?.setText(intent.getStringExtra("keytitle"))

        val input = items("" + note, "", 0)
        itemsViewModel?.update(input)

        itemsViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory(application))
                .get(ItemsViewModel::class.java)

        editNote?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                deleteNote()
            }

            override fun afterTextChanged(s: Editable) {
                newNote = editNote?.text.toString()
            }
        })
    }

    fun showStats(view: View){
        var textLength = editNote?.text?.length
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Statistics")
        builder.setMessage("Characters(with spaces) $textLength")
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onBackPressed() {
        if (newNote != null) {
            val editedNote = items("" + newNote, "", 0)
           itemsViewModel?.insert(editedNote)
        }
        val mainActivity = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivity)
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
        var input = items("hitesh", "", 0)
        itemsViewModel?.delete(input)
    }
}