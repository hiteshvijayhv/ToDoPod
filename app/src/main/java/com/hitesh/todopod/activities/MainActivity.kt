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
import com.hitesh.todopod.R
import com.hitesh.todopod.adapter.RecyclerViewAdapter
import com.hitesh.todopod.items
import com.hitesh.todopod.model.ItemsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val itemsList: List<items> = ArrayList()
    private var mAdapter: RecyclerViewAdapter? = null
    var itemsViewModel: ItemsViewModel? = null

    var isFABOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemsViewModel = ViewModelProvider(this,
                AndroidViewModelFactory(application))
                .get(ItemsViewModel::class.java)


        mAdapter = RecyclerViewAdapter(itemsList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recycler_view?.layoutManager = mLayoutManager
        recycler_view?.itemAnimator = DefaultItemAnimator()
        recycler_view?.adapter = mAdapter

        itemsViewModel?.allitems?.observe(this, Observer { items -> mAdapter?.setNotes(items!!) })

        mAdapter?.setOnItemClickListener(object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(items: items) {
                val editNoteActivity: Intent? = Intent(applicationContext, EditNoteActivity::class.java)
                val title: String? = items.title
                val header: String? = items.description
                editNoteActivity?.putExtra("headertitle", header)
                editNoteActivity?.putExtra("keytitle", title)
                startActivity(editNoteActivity)
                itemsViewModel?.delete(items)
            }
        })

        fab.setOnClickListener{
            if(!isFABOpen) open()
            else close()
        }

        fab1.setOnClickListener {
            val addNote = Intent(applicationContext, AddNoteActivity::class.java)
            startActivity(addNote)
        }

    }

    fun open() {
        fabLayout1.animate().translationY(-50f)
        fab.animate().rotationBy(135f)
        fabLayout1.animate().alpha(1f).duration = 500

        fabLayout1.setVisibility(View.VISIBLE)
        isFABOpen = true
    }

    fun close() {
        fabLayout1.animate().translationY(10f)
        fab.animate().rotationBy(-135f)
        fabLayout1.animate().alpha(0.0f).duration = 500

        isFABOpen = false
    }
}