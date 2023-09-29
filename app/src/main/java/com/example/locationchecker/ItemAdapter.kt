package com.example.locationchecker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.locationchecker.room.Item

class ItemAdapter(var context: Context, var itemList:ArrayList<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name=itemView.findViewById<TextView>(R.id.single_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.single_element, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = itemList[position]
        holder.name.text=currentItem.name

        holder.name.setOnClickListener {
            val i= Intent(context,LocationChecker::class.java)
            i.putExtra("name",currentItem.name)
            i.putExtra("latitude",currentItem.latitude)
            i.putExtra("longitude",currentItem.longitude)

            context.startActivity(i)
        }
    }
}