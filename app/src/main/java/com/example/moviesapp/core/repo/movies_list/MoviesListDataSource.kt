package com.example.moviesapp.core.repo.movies_list

import androidx.paging.PagingData
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_list.Result
import com.example.moviesapp.features.movies_list.models.GenresResponse
import kotlinx.coroutines.flow.Flow

interface MoviesListDataSource {


    suspend fun getGenresListResponse(): Flow<Resource<GenresResponse>>

    fun getMovies(genre: String, query: String, isSearch: Boolean): Flow<PagingData<Result>>
}