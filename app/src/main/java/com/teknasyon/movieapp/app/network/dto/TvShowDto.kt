package com.teknasyon.movieapp.app.network.dto

data class TvShowDto(
    var backdrop_path: String? = null,
    var first_air_date: String? = null,
    var genre_ids: List<String>? = null,
    var id: Int? = null,
    var name: String? = null,
    var origin_country: List<String>? = null,
    var original_language: String? = null,
    var original_name: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var poster_path: String? = null,
    var vote_average: Double? = null,
    var vote_count: Int? = null
)