package com.teknasyon.movieapp.app.di

import com.teknasyon.movieapp.app.data.remotedatasource.TvRemoteDataSource
import com.teknasyon.movieapp.app.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTvRepository(
        remoteDataSource: TvRemoteDataSource
    ) = TvRepository(remoteDataSource)
}