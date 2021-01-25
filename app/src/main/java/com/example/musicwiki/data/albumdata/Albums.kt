package com.example.musicwiki.data.albumdata


import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("album")
    val album: List<Album>,
    @SerializedName("@attr")
    val attr: AttrX
)