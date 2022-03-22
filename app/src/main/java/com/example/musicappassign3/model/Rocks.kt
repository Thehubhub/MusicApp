package com.example.musicappassign3.model


import com.google.gson.annotations.SerializedName

data class Rocks(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val rockItems: List<RockItem>
)