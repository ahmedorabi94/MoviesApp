package com.example.moviesapp.features.movies_list.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviesapp.TestCoroutineRule
import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.genres_list.GenresResponse
import com.orabi.core.domain.usecases.GetGenresListUseCase
import com.orabi.core.domain.usecases.GetMoviesListUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var apiMoviesObserver: Observer<Resource<GenresResponse>>

    @Mock

    private lateinit var viewModel: MoviesListViewModel


    @Mock
    private lateinit var getMoviesListUseCase: GetMoviesListUseCase


    @Mock
    private lateinit var getGenresListUseCase: GetGenresListUseCase


    @Before
    fun setup() {
        // viewModel = MoviesListViewModel(getMoviesListUseCase,getGenresListUseCase)
    }

    @Test
    fun shouldGenresResponseSuccessResponse() {

        val genresResponse = mock(GenresResponse::class.java)

        val result1 = Resource.success(genresResponse)


        val flow = flow {
            emit(result1)
        }
        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(flow)
                .`when`(getGenresListUseCase)
                .invoke()

            viewModel = MoviesListViewModel(getMoviesListUseCase, getGenresListUseCase)

            viewModel.genresResponse.observeForever(apiMoviesObserver)

            Mockito.verify(getGenresListUseCase).invoke()

            Mockito.verify(apiMoviesObserver).onChanged(Resource.success(genresResponse))

            assertEquals(viewModel.genresResponse.value, result1)

            viewModel.genresResponse.removeObserver(apiMoviesObserver)


        }


    }


}