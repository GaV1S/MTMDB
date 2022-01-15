package com.gav1s.mtmdb.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.gav1s.mtmdb.framework.ui.main.MainViewModel
import com.gav1s.mtmdb.model.repository.Repository
import com.gav1s.mtmdb.model.repository.RepositoryImpl

val appModule = module {
    single<Repository> { RepositoryImpl() }

    //View models
    viewModel { MainViewModel(get()) }
}