package com.gav1s.mtmdb.model.repository

import retrofit2.Callback
import com.gav1s.mtmdb.model.entities.Movie
import com.gav1s.mtmdb.model.entities.MoviesList

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    override fun getNewMoviesListFromServer(callback: Callback<MoviesList>) {
        remoteDataSource.getNewMoviesList(callback)
    }

    override fun getMovieDetailsFromServer(
        id: Int,
        callback: Callback<Movie>) {
        remoteDataSource.getMovieDetails(id, callback)
    }
}