package com.gav1s.mtmdb.model.repository

import com.gav1s.mtmdb.model.entities.Movie
import com.gav1s.mtmdb.model.entities.getMovies

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Movie {
        return Movie()
    }

    override fun getWeatherFromLocalStorage() = getMovies()
}