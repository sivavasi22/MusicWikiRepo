package com.example.musicwiki.data.generedata


import com.example.musicwiki.data.generedata.Attr
import com.example.musicwiki.data.generedata.Tag
import com.google.gson.annotations.SerializedName

data class Toptags(
    @SerializedName("@attr")
    val attr: Attr,
    @SerializedName("tag")
    val tag: List<Tag>
)