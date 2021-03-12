package com.teknasyon.movieapp.app.di

import com.teknasyon.movieapp.app.data.remotedatasource.TvRemoteDataSource
import com.teknasyon.movieapp.app.network.service.TvService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideTvRemoteDataSource(tvService: TvService) = TvRemoteDataSource(tvService)
}