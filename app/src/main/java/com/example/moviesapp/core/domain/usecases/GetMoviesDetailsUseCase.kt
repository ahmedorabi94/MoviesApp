package com.example.moviesapp.core.domain.usecases

import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_details.MoviesDetailsResponse
import com.example.moviesapp.core.repo.movies_details.MoviesDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor(private val repository: MoviesDetailsRepository) {


    suspend operator fun invoke(id : Int): Flow<Resource<MoviesDetailsResponse>> {
        return repository.getMoviesDetailsResponse(id)
    }

}