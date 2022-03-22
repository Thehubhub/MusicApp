package com.example.musicappassign3.rest

import com.example.musicappassign3.model.Rocks
import io.reactivex.Single
import retrofit2.http.GET

interface MusicAPI {

    @GET(ROCK_MUSIC_URL)
    fun getRockMusic(): Single<Rocks>

    companion object{
        const val BASE_URL = "https://itunes.apple.com/"
        const val ROCK_MUSIC_URL = BASE_URL + "search?term=rock&amp;media=music&amp;entity=song&amp;limit=50"
    }
}