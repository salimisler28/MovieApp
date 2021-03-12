package com.teknasyon.movieapp.app.network.service

import com.teknasyon.movieapp.BuildConfig
import com.teknasyon.movieapp.app.network.response.GetPopularTvShowsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface TvService {
    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") apiKey: String,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null
    ): Response<GetPopularTvShowsResponse>
}