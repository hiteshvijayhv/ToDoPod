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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hitesh.todopod.R
import com.hitesh.todopod.component.DaggerDateComponent
import com.hitesh.todopod.items
import com.hitesh.todopod.model.DateModel
import com.hitesh.todopod.model.ItemsViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*
import javax.inject.Inject

class EditNoteActivity : AppCompatActivity() {
    var editNote: EditText? = null
    var headerText: EditText? = null
    private var note: String? = null
    private var header: String? = null
    var newNote: String? = null

    var newHeader: String? = null

    private var statsButton: ImageButton? = null

    private var itemsViewModel: ItemsViewModel? = null
    @Inject lateinit var dateModel: DateModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        editNote = findViewById<View>(R.id.editNote) as EditText
        headerText = findViewById<View>(R.id.headerEditText) as EditText

        statsButton = findViewById(R.id.statsButton)

        val intent: Intent = intent
        note = intent.getStringExtra("keytitle")
        header = intent.getStringExtra("headertitle")

        editNote?.setText(intent.getStringExtra("keytitle"))
        headerText?.setText(header)

        val input = items(note, newHeader, 0)
        itemsViewModel?.update(input)


        itemsViewModel = ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory(application))
                .get(ItemsViewModel::class.java)

        val dateModelComponent = DaggerDateComponent.create()
        dateModelComponent.inject(this)

        newNote = editNote?.text.toString()
        newHeader = headerText?.text.toString()

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

        headerText?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                newHeader = headerText?.text.toString()
            }

        })
    }

    fun showStats(view: View){
        val textLength = editNote?.text?.length
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Statistics")
        builder.setMessage("Characters(with spaces) $textLength")
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onBackPressed() {
        if (newNote != null && newHeader != null) {
            val editedNote = items(newNote, newHeader, 0)
            itemsViewModel?.insert(editedNote)
        }
        val mainActivity = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivity)
        super.onBackPressed()
    }

    /**
     * Method to share note
     *
     * @param view
     * @return null
     */
    fun shareNote(view: View){
        val myIntent = Intent(Intent.ACTION_SEND)
        myIntent.type = "text/plain"
        val shareBody = "Share Note"
        val shareSub = ""
        myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
        myIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(myIntent, "Share using"))
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

    /**
     * Method to share note
     *
     * @return null
     */
    private fun deleteNote(){
        val input = items("hitesh", newHeader , 0)
        itemsViewModel?.delete(input)
    }
}