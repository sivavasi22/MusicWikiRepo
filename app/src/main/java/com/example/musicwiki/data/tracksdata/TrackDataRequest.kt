package com.example.musicwiki.data.tracksdata


import com.google.gson.annotations.SerializedName

data class TrackDataRequest(
    @SerializedName("tracks")
    val tracks: Tracks
)