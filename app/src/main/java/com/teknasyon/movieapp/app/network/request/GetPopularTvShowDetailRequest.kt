package com.teknasyon.movieapp.app.network.request

import com.teknasyon.movieapp.BuildConfig
import java.util.*

data class GetPopularTvShowDetailRequest(
    var id: Int,
    var apiKey: String = BuildConfig.API_KEY,
    var language: String? = Locale.getDefault().toLanguageTag(),
    var page: Int? = null
)