package com.example.musicwiki.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

class GenereMenuRecyclerView(
    val activity: FragmentActivity?,
    val userDetails: ArrayList<MainActivity2.Userdetails>,


    ): RecyclerView.Adapter<GenereMenuRecyclerView.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val user_name = itemView.findViewById<TextView>(R.id.album_name)
        val profile_pic = itemView.findViewById<ImageView>(R.id.album_imagge)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.albumlist, parent, false )

        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return  userDetails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: MainActivity2.Userdetails = userDetails[position]
        holder.setIsRecyclable(false)

        holder.run {
            user_name.text = user.specs[0].toString()
        }


        if (user.specs[1].toString() != ""){
            Picasso.get()
                .load(user.specs[1].toString())
                .resize(500,500)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.profile_pic)
        }

        holder.run {

            profile_pic.setOnClickListener {


                    val intent = Intent(activity, MainActivity3::class.java)
                    intent.putExtra("album", user.specs[0].toString())
                    intent.putExtra("image", user.specs[1].toString())
                    intent.putExtra("artist", user.specs[2].toString())


                    activity?.let { it1 -> ContextCompat.startActivity(it1, intent, null) }




            }
        }


    }

}
