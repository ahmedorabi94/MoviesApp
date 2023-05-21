package com.example.moviesapp.features.movies_details.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviesapp.TestCoroutineRule
import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.movies_details.MoviesDetailsResponse
import com.orabi.core.domain.usecases.GetMoviesDetailsUseCase
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
class MoviesDetailsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var apiMoviesObserver: Observer<Resource<MoviesDetailsResponse>>

    private lateinit var viewModel: MoviesDetailsViewModel


    @Mock
    private lateinit var useCase: GetMoviesDetailsUseCase


    @Before
    fun setup() {
        viewModel = MoviesDetailsViewModel(useCase)
    }

    @Test
    fun shouldGetMoviesDetailsSuccessResponse() {

        val moviesResponse = mock(MoviesDetailsResponse::class.java)

        val result1 = Resource.success(moviesResponse)


        val flow = flow {
            emit(result1)
        }
        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(flow)
                .`when`(useCase)
                .invoke(1)

            viewModel.getMoviesDetailsResponse(1)

            viewModel.moviesResponse.observeForever(apiMoviesObserver)

            Mockito.verify(useCase).invoke(1)

            Mockito.verify(apiMoviesObserver).onChanged(Resource.success(moviesResponse))

            assertEquals(viewModel.moviesResponse.value, result1)

            viewModel.moviesResponse.removeObserver(apiMoviesObserver)


        }


    }

}