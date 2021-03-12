package com.teknasyon.movieapp.app.repository

import com.teknasyon.movieapp.app.data.remotedatasource.TvRemoteDataSource
import com.teknasyon.movieapp.app.network.request.GetPopularTvShowsRequest
import com.teknasyon.movieapp.app.util.fromNetwork

class TvRepository constructor(
    private val tvRemoteDataSource: TvRemoteDataSource
) {
    fun getPopularTvShows(
        tvShowsRequest: GetPopularTvShowsRequest
    ) = fromNetwork {
        tvRemoteDataSource.getPopularTvShows(tvShowsRequest)
    }
}