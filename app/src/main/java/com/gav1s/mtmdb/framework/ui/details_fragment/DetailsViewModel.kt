package com.gav1s.mtmdb.framework.ui.details_fragment

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gav1s.mtmdb.model.AppState
import com.gav1s.mtmdb.model.entities.Movie
import com.gav1s.mtmdb.model.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(id: Int) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val data = repository.getMovieFromServer(id)
            liveDataToObserve.postValue(AppState.Success(listOf(data), listOf<Movie>()))
        }.start()
    }
}