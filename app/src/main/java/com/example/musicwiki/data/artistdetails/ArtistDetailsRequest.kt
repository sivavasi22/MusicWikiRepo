package com.example.musicwiki.data.artistdetails


import com.google.gson.annotations.SerializedName

data class ArtistDetailsRequest(
    @SerializedName("artist")
    val artist: Artist
)