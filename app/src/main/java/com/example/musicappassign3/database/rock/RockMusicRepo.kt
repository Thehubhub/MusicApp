package com.example.musicappassign3.database.rock

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class RockMusicRepo(private val rockMusicDAO: RockMusicDAO) {

    val allRockMusic: LiveData<List<RockMusic>> = rockMusicDAO.retrieveMusicLibrary()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(rockMusic: RockMusic){
        rockMusicDAO.insert(rockMusic)
    }
}