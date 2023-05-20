package com.example.moviesapp.core.domain.usecases

import com.example.moviesapp.core.repo.movies_list.MoviesListRepository
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_list.MoviesListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(private val moviesListRepository: MoviesListRepository) {


    suspend operator fun invoke(genre: String): Flow<Resource<MoviesListResponse>> {
        return moviesListRepository.getMoviesListResponse(genre)
    }

}