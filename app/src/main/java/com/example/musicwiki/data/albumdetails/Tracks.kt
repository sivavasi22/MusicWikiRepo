package com.example.musicwiki.data.albumdetails


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("track")
    val track: List<Track>
)