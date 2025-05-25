package com.teya.data.repository

import android.util.Log
import com.teya.data.dto.FeedResponseDto
import com.teya.data.remote.RemoteDataSource
import com.teya.data.remote.ResultWrapper
import javax.inject.Inject


class AlbumRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getAlbuns(): FeedResponseDto? {
        val resultWrapper = remoteDataSource.getAlbumList()

        return when (resultWrapper) {
            is ResultWrapper.Success -> {
                val albums = resultWrapper.value.body()
                Log.d("AlbumRepository", "Fetched albums: ${albums?.feed?.entries?.map { it.name.label }}")
                albums
            }
            is ResultWrapper.GenericError, is ResultWrapper.NetworkError -> {
                Log.e("AlbumRepository", "Failed to fetch albums: $resultWrapper")
                null
            }

            else -> {null}
        }
    }
}