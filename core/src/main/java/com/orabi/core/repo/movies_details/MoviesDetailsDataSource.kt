package com.orabi.core.repo.movies_details

import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.movies_details.MoviesDetailsResponse
import kotlinx.coroutines.flow.Flow

interface MoviesDetailsDataSource {

    suspend fun getMoviesDetailsResponse(id : Int): Flow<Resource<MoviesDetailsResponse>>
}