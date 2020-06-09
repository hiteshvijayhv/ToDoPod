package com.hitesh.todopod.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hitesh.todopod.ItemsViewModel
import com.hitesh.todopod.R
import com.hitesh.todopod.adapter.RecyclerViewAdapter
import com.hitesh.todopod.items
import java.util.*

class MainActivity : AppCompatActivity() {
    private val itemsList: List<items> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: RecyclerViewAdapter? = null
    var itemsViewModel: ItemsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        button.setOnClickListener {
            val text = editText.text.toString()
            val item1 = items("" + text, "", 0)
            itemsViewModel?.insert(item1)
        }
    }
}