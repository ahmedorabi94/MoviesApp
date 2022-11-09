package com.example.moviesapp.core.repo.remote

import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_list.MoviesListResponse
import kotlinx.coroutines.flow.Flow

interface MoviesListDataSource {

    suspend fun getMoviesListResponse(): Flow<Resource<MoviesListResponse>>
}