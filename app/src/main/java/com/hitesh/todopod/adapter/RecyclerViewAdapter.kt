package com.hitesh.todopod.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hitesh.todopod.R
import com.hitesh.todopod.adapter.RecyclerViewAdapter.MyViewHolder
import com.hitesh.todopod.items
import kotlinx.android.synthetic.main.recycler_layout.view.*


class RecyclerViewAdapter(private val itemList: List<items>) : RecyclerView.Adapter<MyViewHolder>() {
    private var listener: OnItemClickListener? = null
    private var items: List<items> = ArrayList()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title: TextView = view.title
        var title2: TextView = view.title2
        var title3: TextView = view.title3

        init {
            view.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener?.onItemClick(items.get(position))
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val items = items[position]
        holder.title.text = items.title
        holder.title2.text = items.description
    }

    fun setNotes(items: List<items?>) {
        this.items = items as List<items>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Define the listener interface
    interface OnItemClickListener {
        fun onItemClick(items: items)
    }

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}