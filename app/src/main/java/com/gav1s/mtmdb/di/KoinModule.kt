package com.gav1s.mtmdb.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.gav1s.mtmdb.framework.ui.details_fragment.DetailsViewModel
import com.gav1s.mtmdb.framework.ui.main_fragment.MainViewModel
import com.gav1s.mtmdb.model.repository.Repository
import com.gav1s.mtmdb.model.repository.RepositoryImpl

val appModule = module {
    single<Repository> { RepositoryImpl(get()) }

    //View models
    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}