package com.example.musicwiki.data.tracksdata


import com.google.gson.annotations.SerializedName

data class Tracks(
    @SerializedName("@attr")
    val attr: Attr,
    @SerializedName("track")
    val track: List<Track>
)