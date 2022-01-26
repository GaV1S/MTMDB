package com.gav1s.mtmdb.model.repository

import com.gav1s.mtmdb.model.entities.Movie

interface Repository {
    fun getMovieFromServer(id: Int): Movie
    fun getNewMoviesFromServer(): List<Movie>
    fun getTopMoviesFromServer(): List<Movie>
}