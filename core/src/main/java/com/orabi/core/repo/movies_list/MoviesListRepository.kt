package com.orabi.core.repo.movies_list

import javax.inject.Inject

class MoviesListRepository @Inject constructor(private val dataSource: MoviesListDataSource) {
    suspend fun getGenresListResponse() = dataSource.getGenresListResponse()
    fun getMovies(genre: String, query: String, isSearch: Boolean) =
        dataSource.getMovies(genre, query, isSearch)

}