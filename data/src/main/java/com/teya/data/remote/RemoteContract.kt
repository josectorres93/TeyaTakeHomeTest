package com.teya.data.remote

import com.teya.data.dto.FeedResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface RemoteContract {
    @GET("us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): Response<FeedResponseDto>
}