package com.teya.data.repository

import com.teya.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlbumModule {
    @Provides
    fun provideAlbumRepository(
        remoteDataSource: RemoteDataSource
    ): AlbumRepository {
        return AlbumRepository(remoteDataSource)
    }
}