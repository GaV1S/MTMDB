package com.gav1s.mtmdb.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesList (
    val results: List<Movie>
): Parcelable