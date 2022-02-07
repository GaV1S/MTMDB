package com.gav1s.mtmdb.framework.ui.history_fragment

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gav1s.mtmdb.model.entities.History
import com.gav1s.mtmdb.model.repository.RemoteDataSource
import com.gav1s.mtmdb.model.repository.RepositoryImpl

class HistoryViewModel(
    private val repository: RepositoryImpl = RepositoryImpl(RemoteDataSource()),
) : ViewModel(), LifecycleObserver {
    val historyLiveData: MutableLiveData<List<History>> = MutableLiveData()

    fun getAllHistory() {
        historyLiveData.postValue(repository.getAllHistory())
    }
}