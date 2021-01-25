package com.example.musicwiki.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.example.musicwiki.data.generedata.Tag

class GenereRecyclerView(val paymentPackagesScreen: MainActivity, val data: List<Tag>) : RecyclerView.Adapter<GenereRecyclerView.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        //initialize the views
        val tempData = itemView.findViewById<TextView>(R.id.tv_label)
        val ClickData = itemView.findViewById<LinearLayout>(R.id.list_view_clickable)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val details = data[position]
        holder.run {
            tempData.text = details.name
        }
        holder.run {
            ClickData.setOnClickListener {
                val intent = Intent(paymentPackagesScreen, MainActivity2::class.java)
                intent.putExtra("rock",details.name)
                ContextCompat.startActivity(paymentPackagesScreen, intent, null)
            }
        }


    }
}