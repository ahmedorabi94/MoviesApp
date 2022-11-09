package com.example.moviesapp.core.repo.movies_details

import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_details.MoviesDetailsResponse
import kotlinx.coroutines.flow.Flow

interface MoviesDetailsDataSource {

    suspend fun getMoviesDetailsResponse(id : Int): Flow<Resource<MoviesDetailsResponse>>
}