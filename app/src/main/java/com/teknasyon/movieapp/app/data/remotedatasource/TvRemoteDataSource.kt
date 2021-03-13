package com.teknasyon.movieapp.app.data.remotedatasource

import com.teknasyon.movieapp.app.base.BaseRemoteDataSource
import com.teknasyon.movieapp.app.network.request.GetPopularTvShowDetailRequest
import com.teknasyon.movieapp.app.network.request.GetPopularTvShowsRequest
import com.teknasyon.movieapp.app.network.service.TvService

class TvRemoteDataSource constructor(
    private val tvService: TvService
) : BaseRemoteDataSource() {
    suspend fun getPopularTvShows(tvShowsRequest: GetPopularTvShowsRequest) = getResult {
        tvService.getPopularTvShows(
            apiKey = tvShowsRequest.apiKey,
            language = tvShowsRequest.language,
            page = tvShowsRequest.page
        )
    }

    suspend fun getTvShowDetail(tvShowDetailRequest: GetPopularTvShowDetailRequest) = getResult {
        tvService.getPopularTvShow(
            id = tvShowDetailRequest.id,
            apiKey = tvShowDetailRequest.apiKey,
            language = tvShowDetailRequest.language,
            page = tvShowDetailRequest.page,
        )
    }
}