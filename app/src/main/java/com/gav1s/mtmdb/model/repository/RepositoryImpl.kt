package com.gav1s.mtmdb.model.repository

import com.gav1s.mtmdb.model.entities.Movie
import com.gav1s.mtmdb.model.entities.getNewMovies
import com.gav1s.mtmdb.model.entities.getTopMovies

class RepositoryImpl : Repository {
    override fun getMovieFromServer(): Movie {
        return Movie()
    }

    override fun getNewMovieFromLocalStorage() = getNewMovies()
    override fun getTopMovieFromLocalStorage() = getTopMovies()
}