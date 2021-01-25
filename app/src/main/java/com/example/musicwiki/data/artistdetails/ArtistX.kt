package com.example.musicwiki.data.artistdetails


import com.google.gson.annotations.SerializedName

data class ArtistX(
    @SerializedName("image")
    val image: List<ImageX>,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)