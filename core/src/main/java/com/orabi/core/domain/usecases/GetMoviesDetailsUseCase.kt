package com.orabi.core.domain.usecases

import com.orabi.core.repo.movies_details.MoviesDetailsRepository
import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.movies_details.MoviesDetailsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor(private val repository: MoviesDetailsRepository) {


    suspend operator fun invoke(id : Int): Flow<Resource<MoviesDetailsResponse>> {
        return repository.getMoviesDetailsResponse(id)
    }

}