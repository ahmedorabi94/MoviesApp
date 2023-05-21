package com.orabi.core.repo.movies_details

import com.orabi.core.repo.movies_details.MoviesDetailsDataSource
import javax.inject.Inject

class MoviesDetailsRepository @Inject constructor(private val dataSource: MoviesDetailsDataSource) {

    suspend fun getMoviesDetailsResponse(id : Int) = dataSource.getMoviesDetailsResponse(id)

}