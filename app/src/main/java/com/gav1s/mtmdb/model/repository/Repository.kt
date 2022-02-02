package com.gav1s.mtmdb.model.repository

import com.gav1s.mtmdb.model.entities.Movie
import com.gav1s.mtmdb.model.entities.MoviesList

interface Repository {
    fun getNewMoviesListFromServer(
        callback: retrofit2.Callback<MoviesList>
    )

    fun getMovieDetailsFromServer(
        id: Int,
        callback: retrofit2.Callback<Movie>
    )
}