package com.gav1s.mtmdb.model.repository

import com.gav1s.mtmdb.model.entities.Credits
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CreditsAPI {
    @GET("3/movie/{movie_id}/credits")
    fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Call<Credits>
}