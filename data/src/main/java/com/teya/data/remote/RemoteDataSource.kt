package com.teya.data.remote

import com.teya.data.dto.FeedResponseDto
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val request: RemoteContract) {
    suspend fun getAlbumList(): ResultWrapper<Response<FeedResponseDto>> {
        return safeApiCall {
            request.getTopAlbums()
        }
    }
}