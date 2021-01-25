package com.example.musicwiki.data.albumdata


import com.google.gson.annotations.SerializedName

data class AlbumDataRequest(
    @SerializedName("albums")
    val albums: Albums
)