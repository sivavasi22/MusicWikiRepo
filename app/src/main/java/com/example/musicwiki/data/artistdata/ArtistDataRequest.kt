package com.example.musicwiki.data.artistdata


import com.google.gson.annotations.SerializedName

data class ArtistDataRequest(
    @SerializedName("topartists")
    val topartists: Topartists
)