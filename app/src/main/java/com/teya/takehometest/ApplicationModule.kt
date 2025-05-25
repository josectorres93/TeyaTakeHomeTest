package com.teya.takehometest

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Named("application")
    fun provideApplication(): Application {
        return MainApplication.application
    }
}
