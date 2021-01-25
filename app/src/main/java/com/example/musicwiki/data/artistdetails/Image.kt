package com.example.musicwiki.data.artistdetails


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("size")
    val size: String,
    @SerializedName("#text")
    val text: String
)