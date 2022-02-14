package com.gav1s.mtmdb.model.repository

import com.gav1s.mtmdb.model.entities.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonAPI {
    @GET("3/person/{person_id}")
    fun getPerson(
        @Path("person_id") personId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Call<Person>
}