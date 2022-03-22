package com.example.musicappassign3.database.rock

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicappassign3.database.pop.PopMusic

@Database(entities = arrayOf(PopMusic::class), version = 1, exportSchema = false)
public abstract class RockDatabase : RoomDatabase() {

    abstract fun rockMusicDAO(): RockMusicDAO

    companion object {

        @Volatile
        private var INSTANCE: RockDatabase? = null

        fun getDatabase(context: Context): RockDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RockDatabase::class.java,
                    "rock_music_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
