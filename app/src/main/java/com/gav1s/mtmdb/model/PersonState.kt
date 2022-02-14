package com.gav1s.mtmdb.model

import com.gav1s.mtmdb.model.entities.Person

sealed class PersonState {
    data class Success(val personData: Person) : PersonState()
    data class Error(val error: Throwable) : PersonState()
    object Loading : PersonState()
}