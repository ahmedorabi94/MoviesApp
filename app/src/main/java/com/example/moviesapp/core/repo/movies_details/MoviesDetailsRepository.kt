package com.example.moviesapp.core.repo.movies_details

import javax.inject.Inject

class MoviesDetailsRepository @Inject constructor(private val dataSource: MoviesDetailsDataSource) {

    suspend fun getMoviesDetailsResponse(id : Int) = dataSource.getMoviesDetailsResponse(id)

}