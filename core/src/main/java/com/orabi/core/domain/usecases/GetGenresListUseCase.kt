package com.orabi.core.domain.usecases

import com.orabi.core.repo.movies_list.MoviesListRepository
import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.genres_list.GenresResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresListUseCase @Inject constructor(private val moviesListRepository: MoviesListRepository) {


    suspend operator fun invoke(): Flow<Resource<GenresResponse>> {
        return moviesListRepository.getGenresListResponse()
    }

}