package com.example.musicwiki.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.musicwiki.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main4.*


class MainActivity4 : AppCompatActivity() {
    private lateinit var ViewModel: ArtistDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        ViewModel = ViewModelProviders.of(this)[com.example.musicwiki.main.ArtistDetailsViewModel::class.java]
val artist = intent.getStringExtra("artist")
        val image = intent.getStringExtra("image")
        artist?.let { ViewModel.albumdatavm(it) }
        image?.let { setupObservers(it) }
    }

    fun setupObservers(image:String) {

        //observing the response received from the api
        ViewModel.ArtistDetailsresponseLiveWeatherData.observe(this, Observer {
            //if not empty data initalizing the recyl view and setting the weather details


                albumdetails_name4.text = it.artist.name.toString()
                albumdetails_description4.text = it.artist.bio.summary
                val followers = it.artist.stats.listeners
                val count = it.artist.stats.playcount
                playcount.text = count
                Followers.text = followers
                Picasso.get()
                    .load(image)
                    .resize(600,600)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(album_details_imageView4)



        })
        //observe the status code of the api hit, whether it is a success or fail

    }
}