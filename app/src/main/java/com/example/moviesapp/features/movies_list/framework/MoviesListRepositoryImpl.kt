package com.example.moviesapp.features.movies_list.framework

import com.example.moviesapp.core.data.api.ApiService
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.data.api.ResultWrapper
import com.example.moviesapp.core.data.api.safeApiCall
import com.example.moviesapp.core.domain.model.movies_list.MoviesListResponse
import com.example.moviesapp.core.repo.movies_list.MoviesListDataSource
import com.example.moviesapp.features.movies_list.models.GenresResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MoviesListRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MoviesListDataSource {


    override suspend fun getMoviesListResponse(genre: String): Flow<Resource<MoviesListResponse>> {
        return flow {

            emit(Resource.loading(null))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getMoviesListAsync(genre)
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

    override suspend fun getSearchMoviesListResponse(query: String): Flow<Resource<MoviesListResponse>> {
        return flow {

            emit(Resource.loading(null))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getSearchMovieAsync(query)
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