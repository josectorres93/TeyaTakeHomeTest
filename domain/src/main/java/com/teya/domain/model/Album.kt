package com.teya.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val name: String,
    val artist: String,
    val imageUrl: String,
    val category: String,
    val releaseDate: String,
    val title: String
) : Parcelable