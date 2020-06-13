package com.hitesh.todopod.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hitesh.todopod.model.ItemsViewModel
import com.hitesh.todopod.R
import com.hitesh.todopod.adapter.RecyclerViewAdapter
import com.hitesh.todopod.items
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val itemsList: List<items> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerViewAdapter? = null
    var itemsViewModel: ItemsViewModel? = null

    var isFABOpen = false
    var fabLayout1: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabLayout1 = findViewById(R.id.fabLayout1)

        itemsViewModel = ViewModelProvider(this,
                AndroidViewModelFactory(application))
                .get(ItemsViewModel::class.java)

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        mAdapter = RecyclerViewAdapter(itemsList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView?.layoutManager = mLayoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = mAdapter

        itemsViewModel?.allitemss?.observe(this, Observer { items -> mAdapter?.setNotes(items!!) })

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)



        mAdapter?.setOnItemClickListener(object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(items: items) {
                var editNoteActivity: Intent? = Intent(applicationContext, EditNoteActivity::class.java)
                var title: String? = items.title
                editNoteActivity?.putExtra("keytitle", title)
                startActivity(editNoteActivity)
                itemsViewModel?.delete(items)
            }
        })

        button.setOnClickListener {
            val text = editText.text.toString()
            val item1 = items("" + text, "", 0)
            itemsViewModel?.insert(item1)
        }
        fab.setOnClickListener{
            if(!isFABOpen){
                open();
                fabLayout1?.setVisibility(View.VISIBLE);
                isFABOpen = true;
            } else {
                close();
                isFABOpen = false;
            }
            Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
        }

        fab1.setOnClickListener {
            Toast.makeText(applicationContext, "clicked", Toast.LENGTH_SHORT).show()
            val addNote = Intent(applicationContext, AddNoteActivity::class.java)
            startActivity(addNote)
        }
    }
    fun open() {
        fabLayout1!!.animate().translationY(-50f)
        fab.animate().rotationBy(135f)
        fabLayout1!!.animate().alpha(1f).duration = 500
    }

    fun close() {
        fabLayout1!!.animate().translationY(10f)
        fab.animate().rotationBy(-135f)
        fabLayout1!!.animate().alpha(0.0f).duration = 500
    }

}