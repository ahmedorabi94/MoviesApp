package com.example.moviesapp.features.movies_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_list.MoviesListResponse
import com.example.moviesapp.core.domain.usecases.GetGenresListUseCase
import com.example.moviesapp.core.domain.usecases.GetMoviesListUseCase
import com.example.moviesapp.core.domain.usecases.GetSearchUseCase
import com.example.moviesapp.features.movies_list.models.GenresResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getGenresUseCase: GetGenresListUseCase,
    private val searchUseCase: GetSearchUseCase,
) :
    ViewModel() {

    private val _moviesResponse = MutableLiveData<Resource<MoviesListResponse>>()
    val moviesResponse: LiveData<Resource<MoviesListResponse>>
        get() = _moviesResponse


    private val _genresResponse = MutableLiveData<Resource<GenresResponse>>()
    val genresResponse: LiveData<Resource<GenresResponse>>
        get() = _genresResponse


    init {

        getMoviesListFlow("28")
        getGenresListFlow()
    }


     fun getMoviesListFlow(genre: String) {
        viewModelScope.launch {
            getMoviesListUseCase.invoke(genre)
                .collect { response ->
                    Timber.e(response.toString())
                    _moviesResponse.value = response
                }
        }

    }

    fun getSearchMoviesListFlow(query: String) {
        viewModelScope.launch {
            searchUseCase.invoke(query)
                .collect { response ->
                    Timber.e(response.toString())
                    _moviesResponse.value = response
                }
        }

    }

    private fun getGenresListFlow() {
        viewModelScope.launch {
            getGenresUseCase.invoke()
                .collect { response ->
                    Timber.e(response.toString())
                    _genresResponse.value = response
                }
        }

    }

}