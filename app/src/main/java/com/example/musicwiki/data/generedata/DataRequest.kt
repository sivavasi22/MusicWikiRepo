package com.example.musicwiki.data.generedata


import com.google.gson.annotations.SerializedName

data class DataRequest(
    @SerializedName("toptags")
    val toptags: Toptags
)