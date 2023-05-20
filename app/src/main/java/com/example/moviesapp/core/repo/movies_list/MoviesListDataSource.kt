package com.example.moviesapp.core.repo.movies_list

import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_list.MoviesListResponse
import com.example.moviesapp.features.movies_list.models.GenresResponse
import kotlinx.coroutines.flow.Flow

interface MoviesListDataSource {

    suspend fun getMoviesListResponse(genre: String): Flow<Resource<MoviesListResponse>>
    suspend fun getSearchMoviesListResponse(query: String): Flow<Resource<MoviesListResponse>>
    suspend fun getGenresListResponse(): Flow<Resource<GenresResponse>>
}