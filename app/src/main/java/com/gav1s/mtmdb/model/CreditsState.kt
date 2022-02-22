package com.gav1s.mtmdb.model

import com.gav1s.mtmdb.model.entities.Cast

sealed class CreditsState {
    data class Success(val creditsData: List<Cast>) : CreditsState()
    data class Error(val error: Throwable) : CreditsState()
}