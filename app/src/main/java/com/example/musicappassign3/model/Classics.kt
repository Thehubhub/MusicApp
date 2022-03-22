package com.example.musicappassign3.model


import com.google.gson.annotations.SerializedName

data class Classics(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val classicItems: List<ClassicItem>
)