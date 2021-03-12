package com.teknasyon.movieapp.app.network.dto

data class SeasonDto(
    var air_date: String? = null,
    var episode_count: Int? = null,
    var id: Int? = null,
    var name: String? = null,
    var overview: String? = null,
    var poster_path: String? = null,
    var season_number: Int? = null,
)
