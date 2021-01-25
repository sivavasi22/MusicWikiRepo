package com.example.musicwiki.data.albumdata


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)