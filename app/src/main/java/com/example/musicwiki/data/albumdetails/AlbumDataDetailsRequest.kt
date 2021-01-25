package com.example.musicwiki.data.albumdetails


import com.google.gson.annotations.SerializedName

data class AlbumDataDetailsRequest(
    @SerializedName("album")
    val album: Album
)