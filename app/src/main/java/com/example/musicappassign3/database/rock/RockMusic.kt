package com.example.musicappassign3.database.rock

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rock_genre")
class RockMusic(
    @PrimaryKey(autoGenerate = true) val rockId: Int,
    @ColumnInfo(name = "rock_title") val rockTitle: String?,
    @ColumnInfo(name = "rock_artist") val rockArtist: String?,
    @ColumnInfo(name = "rock_price") val rockPrice: String?
) {
}