package com.gav1s.mtmdb.framework.ui.main_fragment

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.gav1s.mtmdb.model.AppState
import com.gav1s.mtmdb.model.entities.MoviesList
import com.gav1s.mtmdb.model.repository.*
import java.lang.Thread.sleep

class MainViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(RemoteDataSource()),
) : ViewModel(), LifecycleObserver {
    val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun getNewDataFromServer() {
        liveData.value = AppState.Loading
        repository.getNewMoviesListFromServer(callback)
    }

    private val callback = object :
        Callback<MoviesList> {
        override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
            val serverResponse: MoviesList? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<MoviesList>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(serverResponse: MoviesList): AppState {
            return AppState.Success(serverResponse.results)
        }
    }
}