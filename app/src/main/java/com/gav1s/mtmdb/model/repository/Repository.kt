package com.gav1s.mtmdb.model.repository

import com.gav1s.mtmdb.model.entities.*
import retrofit2.Callback

interface Repository {
    fun getNewMoviesListFromServer(
        callback: retrofit2.Callback<MoviesList>
    )

    fun getMovieDetailsFromServer(
        id: Int,
        callback: retrofit2.Callback<Movie>
    )

    fun saveToHistory(history: History)

    fun getAllHistory(): List<History>
    fun getCreditsFromServer(id: Int, callback: Callback<Credits>)
    fun getPersonFromServer(id: Int, callback: Callback<Person>)
}