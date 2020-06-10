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


class RecyclerViewAdapter(private val itemList: List<items>) : RecyclerView.Adapter<MyViewHolder>() {
    private var listener: OnItemClickListener? = null

    private var items: List<items> = ArrayList()

    var context: Context? = null

    // Define the listener interface
    public interface OnItemClickListener {
        fun onItemClick(items: items)
    }

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title: TextView
        var title2: TextView
        var title3: TextView

        init {
            title = view.findViewById<View>(R.id.title) as TextView
            title2 = view.findViewById<View>(R.id.title2) as TextView
            title3 = view.findViewById<View>(R.id.title3) as TextView
            view.setOnClickListener { // Triggers click upwards to the adapter on click
                if (listener != null) {
                    val position = adapterPosition
                    if (listener != null && position != RecyclerView.NO_POSITION){
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
    fun setNotes(items: List<items?>) {
        this.items = items as List<items>
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val items = items[position]
        holder.title.text = items.title
        //holder.title2.text = items.genre
        //holder.title3.text = items.year
    }

    override fun getItemCount(): Int {
        return items.size
    }
}