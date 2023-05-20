package com.example.moviesapp.features.movies_details.framework

import com.example.moviesapp.core.data.api.ApiService
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.data.api.ResultWrapper
import com.example.moviesapp.core.data.api.safeApiCall
import com.example.moviesapp.core.domain.model.movies_details.MoviesDetailsResponse
import com.example.moviesapp.core.repo.movies_details.MoviesDetailsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MoviesDetailsRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MoviesDetailsDataSource {


    override suspend fun getMoviesDetailsResponse(id : Int): Flow<Resource<MoviesDetailsResponse>> {

        return flow {

            emit(Resource.loading(null))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getMoviesDetails(id)
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