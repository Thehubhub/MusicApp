package com.example.musicappassign3.database.rock

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RockMusicDAO {
    @Query("SELECT * FROM rock_genre")
    fun retrieveMusicLibrary(): LiveData<List<RockMusic>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(rockMusic: RockMusic)

    @Query("DELETE FROM rock_genre")
    fun deleteAll()
}