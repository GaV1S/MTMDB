package com.gav1s.mtmdb.model

import com.gav1s.mtmdb.model.entities.Movie

sealed class AppState {
    data class Success(val moviesData: List<Movie>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}