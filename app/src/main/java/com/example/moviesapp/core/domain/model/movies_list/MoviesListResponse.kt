package com.example.moviesapp.core.domain.model.movies_list

data class MoviesListResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)