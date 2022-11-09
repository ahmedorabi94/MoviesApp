package com.example.moviesapp.features.movies_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_list.MoviesListResponse
import com.example.moviesapp.core.domain.usecases.remote.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
) :
    ViewModel() {

    private val _moviesResponse = MutableLiveData<Resource<MoviesListResponse>>()
    val moviesResponse: LiveData<Resource<MoviesListResponse>>
        get() = _moviesResponse



    init {

        getMoviesListFlow()
    }


    private fun getMoviesListFlow() {
        viewModelScope.launch {
            getMoviesListUseCase.invoke()
                .collect { response ->
                    Timber.e(response.toString())
                    _moviesResponse.value = response
                }
        }

    }

}