package com.gav1s.mtmdb.model.rest_entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListMoviesDTO (
    val results: List<MovieDTO>
) : Parcelable