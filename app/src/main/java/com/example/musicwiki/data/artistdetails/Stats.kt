package com.example.musicwiki.data.artistdetails


import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("playcount")
    val playcount: String
)