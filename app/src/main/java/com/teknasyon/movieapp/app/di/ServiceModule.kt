package com.teknasyon.movieapp.app.di

import com.teknasyon.movieapp.app.network.service.TvService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideTvService(retrofit: Retrofit): TvService = retrofit.create(TvService::class.java)
}