package com.example.moviesapp.features.movies_list.framework

import com.example.moviesapp.core.data.api.ApiService
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.data.api.ResultWrapper
import com.example.moviesapp.core.data.api.safeApiCall
import com.example.moviesapp.core.domain.model.movies_list.MoviesListResponse
import com.example.moviesapp.core.repo.remote.MoviesListDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MoviesListRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MoviesListDataSource {


    override suspend fun getMoviesListResponse(): Flow<Resource<MoviesListResponse>> {
        return flow {

            emit(Resource.loading(null))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getMoviesListAsync()
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