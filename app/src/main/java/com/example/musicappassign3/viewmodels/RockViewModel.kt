package com.example.musicappassign3.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.musicappassign3.database.rock.RockDatabase
import com.example.musicappassign3.database.rock.RockMusic
import com.example.musicappassign3.database.rock.RockMusicRepo

class RockViewModel(application: Application): AndroidViewModel(application) {

    private val repository: RockMusicRepo
    private val allRockMusic: LiveData<List<RockMusic>>

    init {
        val rockMusicDAO = RockDatabase.getDatabase(application).rockMusicDAO()
        repository = RockMusicRepo(rockMusicDAO)
        allRockMusic = repository.allRockMusic
    }
}