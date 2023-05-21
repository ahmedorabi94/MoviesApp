package com.orabi.core.repo.movies_list

import androidx.paging.PagingData
import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.genres_list.GenresResponse
import com.orabi.core.domain.model.movies_list.Result
import kotlinx.coroutines.flow.Flow

interface MoviesListDataSource {


    suspend fun getGenresListResponse(): Flow<Resource<GenresResponse>>

    fun getMovies(genre: String, query: String, isSearch: Boolean): Flow<PagingData<Result>>
}