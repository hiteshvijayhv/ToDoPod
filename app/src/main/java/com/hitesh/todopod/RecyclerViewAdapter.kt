package com.hitesh.todopod

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hitesh.todopod.RecyclerViewAdapter.MyViewHolder

class RecyclerViewAdapter(private val itemList: List<items>) : RecyclerView.Adapter<MyViewHolder>() {
    private var listener: OnItemClickListener? = null

    // Define the listener interface
    interface OnItemClickListener {
        fun onItemClick(itemView: View?, position: Int)
    }

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var title: TextView
        var title2: TextView
        var title3: TextView
        override fun onClick(v: View) {}

        init {
            title = view.findViewById<View>(R.id.title) as TextView
            title2 = view.findViewById<View>(R.id.title2) as TextView
            title3 = view.findViewById<View>(R.id.title3) as TextView
            view.setOnClickListener { // Triggers click upwards to the adapter on click
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener?.onItemClick(itemView, position)
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
        val items = itemList[position]
        holder.title.text = items.title
        holder.title2.text = items.genre
        holder.title3.text = items.year
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}