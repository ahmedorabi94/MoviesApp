package com.example.moviesapp.core.domain.usecases

import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.repo.movies_list.MoviesListRepository
import com.example.moviesapp.features.movies_list.models.GenresResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresListUseCase @Inject constructor(private val moviesListRepository: MoviesListRepository) {


    suspend operator fun invoke(): Flow<Resource<GenresResponse>> {
        return moviesListRepository.getGenresListResponse()
    }

}