package com.teknasyon.movieapp.app.network.response

import com.teknasyon.movieapp.app.network.dto.TvShowDto

data class GetPopularTvShowsResponse(
    var page: Int? = null,
    var results: List<TvShowDto>? = null,
    var total_pages: Int? = null,
    var total_results: Int? = null
)