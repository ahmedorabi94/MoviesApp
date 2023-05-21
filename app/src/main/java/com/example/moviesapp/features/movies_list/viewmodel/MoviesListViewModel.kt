package com.example.moviesapp.features.movies_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapp.features.utils.MovieSearch
import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.genres_list.GenresResponse
import com.orabi.core.domain.model.movies_list.Result
import com.orabi.core.domain.usecases.GetGenresListUseCase
import com.orabi.core.domain.usecases.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val getGenresUseCase: GetGenresListUseCase
) :
    ViewModel() {


    private val _genresResponse = MutableLiveData<Resource<GenresResponse>>()
    val genresResponse: LiveData<Resource<GenresResponse>>
        get() = _genresResponse


    val movieSearch = MutableStateFlow(MovieSearch("28", ""))
    var isSearch = false


    val items = movieSearch
        .debounce(500L)
        .filter { query ->
            return@filter query.query.isNotEmpty() || query.genre.isNotEmpty()
        }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            getMovies(query.genre, query.query, isSearch)
        }
        .flowOn(Dispatchers.IO)


    init {

        getGenresListFlow()

    }

    private fun getMovies(
        genre: String = "28",
        query: String = "",
        isSearch: Boolean
    ): Flow<PagingData<Result>> {

        return getMoviesListUseCase.getMovies(genre, query, isSearch)
            .cachedIn(viewModelScope)
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