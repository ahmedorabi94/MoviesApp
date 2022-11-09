package com.example.moviesapp.core.repo.remote

import javax.inject.Inject

class MoviesListRepository @Inject constructor(private val dataSource: MoviesListDataSource) {

    suspend fun getMoviesListResponse() = dataSource.getMoviesListResponse()

}