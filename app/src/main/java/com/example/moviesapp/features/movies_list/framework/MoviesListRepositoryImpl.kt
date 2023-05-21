package com.example.moviesapp.features.movies_list.framework

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orabi.core.data.api.ApiService
import com.orabi.core.domain.model.movies_list.Result
import com.orabi.core.repo.movies_list.MoviesListDataSource
import com.example.moviesapp.features.utils.NETWORK_PAGE_SIZE
import com.orabi.core.data.api.MoviesPagingSource
import com.orabi.core.data.api.Resource
import com.orabi.core.data.api.ResultWrapper
import com.orabi.core.data.api.safeApiCall
import com.orabi.core.domain.model.genres_list.GenresResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MoviesListRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MoviesListDataSource {


    override fun getMovies(
        genre: String,
        query: String,
        isSearch: Boolean
    ): Flow<PagingData<Result>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service = apiService, genre, query, isSearch)
            }
        ).flow
    }

    override suspend fun getGenresListResponse(): Flow<Resource<GenresResponse>> {
        return flow {

            emit(Resource.loading(null))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getGenresListAsync()
            }

            when (response) {
                is ResultWrapper.Success -> {
                    emit(Resource.success(response.value))
                }

                is ResultWrapper.Error -> {
                    emit(Resource.error(response.error?.message ?: "Unknown Error"))

                }

                is ResultWrapper.NetworkError -> {
                    emit(Resource.error("NetworkError"))

                }

                ResultWrapper.NoContentError -> {
                    emit(Resource.error("NoContentError"))

                }
            }

        }.flowOn(Dispatchers.IO)
    }


}