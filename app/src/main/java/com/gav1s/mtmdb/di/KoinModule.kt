package com.gav1s.mtmdb.di

import com.gav1s.mtmdb.framework.ui.details_fragment.DetailsViewModel
import com.gav1s.mtmdb.framework.ui.main_fragment.MainViewModel
import com.gav1s.mtmdb.model.repository.Repository
import com.gav1s.mtmdb.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { SettingsViewModel() }
    viewModel { HistoryViewModel(get()) }
}