package com.gav1s.mtmdb.model.repository

import com.gav1s.mtmdb.model.entities.Movie

interface Repository {
    fun getMovieFromServer(): Movie
    fun getNewMovieFromLocalStorage(): List<Movie>
    fun getTopMovieFromLocalStorage(): List<Movie>
}