package com.example.musicwiki.data.albumdetails


import com.google.gson.annotations.SerializedName

data class Wiki(
    @SerializedName("content")
    val content: String,
    @SerializedName("published")
    val published: String,
    @SerializedName("summary")
    val summary: String
)