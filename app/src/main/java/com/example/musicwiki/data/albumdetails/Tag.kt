package com.example.musicwiki.data.albumdetails


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)