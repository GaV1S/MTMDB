package com.gav1s.mtmdb.model

import com.gav1s.mtmdb.model.entities.Movie

sealed class MovieState {
    data class Success(val moviesData: List<Movie>) : MovieState()
    data class Error(val error: Throwable) : MovieState()
    object Loading : MovieState()
}