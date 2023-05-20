package com.example.moviesapp.core.repo.movies_list

import javax.inject.Inject

class MoviesListRepository @Inject constructor(private val dataSource: MoviesListDataSource) {

    suspend fun getMoviesListResponse(genre: String) = dataSource.getMoviesListResponse(genre)

    suspend fun getSearchMoviesListResponse(query: String) = dataSource.getSearchMoviesListResponse(query)

    suspend fun getGenresListResponse() = dataSource.getGenresListResponse()

}