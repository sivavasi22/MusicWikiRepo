package com.example.musicwiki.data.artistdetails


import com.google.gson.annotations.SerializedName

data class Similar(
    @SerializedName("artist")
    val artist: List<ArtistX>
)