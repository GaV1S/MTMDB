package com.gav1s.mtmdb.framework.ui.details_fragment

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gav1s.mtmdb.model.AppState
import com.gav1s.mtmdb.model.entities.History
import com.gav1s.mtmdb.model.entities.Movie
import com.gav1s.mtmdb.model.repository.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(RemoteDataSource()),
) : ViewModel(), LifecycleObserver{
    val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getMovieFromRemoteSource(id: Int) {
        liveData.value = AppState.Loading
        repository.getMovieDetailsFromServer(id, callback)
    }

    fun saveToHistory(history: History) {
        repository.saveToHistory(history)
    }

    private val callback = object :
        Callback<Movie> {
        override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
            val serverResponse: Movie? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<Movie>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: Movie): AppState {
            return if (serverResponse.overview == null) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(listOf(serverResponse))
            }
        }
    }
}