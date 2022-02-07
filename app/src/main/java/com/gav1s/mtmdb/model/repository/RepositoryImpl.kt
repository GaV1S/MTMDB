package com.gav1s.mtmdb.model.repository

import retrofit2.Callback
import com.gav1s.mtmdb.model.database.Database
import com.gav1s.mtmdb.model.database.HistoryEntity
import com.gav1s.mtmdb.model.entities.History
import com.gav1s.mtmdb.model.entities.Movie
import com.gav1s.mtmdb.model.entities.MoviesList

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override fun getNewMoviesListFromServer(callback: Callback<MoviesList>) {
        remoteDataSource.getNewMoviesList(callback)
    }

    override fun getMovieDetailsFromServer(id: Int, callback: Callback<Movie>) {
        remoteDataSource.getMovieDetails(id, callback)
    }

    override fun saveToHistory(history: History) {
        Database.db.historyDao().insert(convertHistoryToEntity(history))
    }

    override fun getAllHistory(): List<History> {
        return convertHistoryEntityToHistory(Database.db.historyDao().all())
    }

    private fun convertHistoryEntityToHistory(entityList: List<HistoryEntity>): List<History> =
        entityList.map {
            History(it.movie_id, it.movie_title, it.time)
        }


    private fun convertHistoryToEntity(history: History): HistoryEntity =
        HistoryEntity(0, history.movie_id,
            history.movie_title,
            history.time
        )
}