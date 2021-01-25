package com.example.musicwiki.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.musicwiki.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main3.*



class MainActivity3 : AppCompatActivity() {
    private lateinit var ViewModel: AlbumDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        ViewModel = ViewModelProviders.of(this)[com.example.musicwiki.main.AlbumDetailsViewModel::class.java]
val artist = intent.getStringExtra("artist")
        val album = intent.getStringExtra("album")
        val image = intent.getStringExtra("image")

        artist?.let { album?.let { it1 -> ViewModel.albumdatavm(it, it1) } }
        setupObservers(image)
    }

    fun setupObservers(imageView: String?) {

        //observing the response received from the api
        ViewModel.AlbumDetailsresponseLiveWeatherData.observe(this, Observer {
            //if not empty data initalizing the recyl view and setting the weather details
            if (it.album.image.size!= 0) {

                albumdetails_name.text = it.album.name.toString()
                albumdetails_description.text = it.album.wiki.content
                Picasso.get()
                    .load(imageView)
                    .resize(600,600)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(album_details_imageView)
            }

            else {
                Toast.makeText(this, "Not Successful", Toast.LENGTH_SHORT).show()
            }
        })
        //observe the status code of the api hit, whether it is a success or fail

    }
}