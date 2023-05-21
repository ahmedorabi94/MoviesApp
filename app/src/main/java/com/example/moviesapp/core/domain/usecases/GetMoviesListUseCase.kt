package com.example.moviesapp.core.domain.usecases

import androidx.paging.PagingData
import com.example.moviesapp.core.domain.model.movies_list.Result
import com.example.moviesapp.core.repo.movies_list.MoviesListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(private val moviesListRepository: MoviesListRepository) {

    fun getMovies(genre: String, query: String, isSearch: Boolean): Flow<PagingData<Result>> {
        return moviesListRepository.getMovies(genre, query, isSearch)
    }


}