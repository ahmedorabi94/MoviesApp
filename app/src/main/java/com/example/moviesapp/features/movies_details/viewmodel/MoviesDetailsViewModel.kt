package com.example.moviesapp.features.movies_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_details.MoviesDetailsResponse
import com.example.moviesapp.core.domain.usecases.GetMoviesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val useCase: GetMoviesDetailsUseCase
) :
    ViewModel() {

    private val _moviesResponse = MutableLiveData<Resource<MoviesDetailsResponse>>()
    val moviesResponse: LiveData<Resource<MoviesDetailsResponse>>
        get() = _moviesResponse


    fun getMoviesDetailsResponse(name: Int) {
        viewModelScope.launch {
            useCase.invoke(name)
                .collect { response ->
                    Timber.e(response.toString())
                    _moviesResponse.value = response
                }
        }

    }

}