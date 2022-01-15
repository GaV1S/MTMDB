package com.gav1s.mtmdb.model.repository

import com.gav1s.mtmdb.model.entities.Movie

interface Repository {
    fun getWeatherFromServer(): Movie
    fun getWeatherFromLocalStorage(): List<Movie>
}