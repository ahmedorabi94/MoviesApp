package com.example.moviesapp.features.movies_details.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.TestCoroutineRule
import com.orabi.core.data.api.ApiService
import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.movies_details.MoviesDetailsResponse
import org.junit.jupiter.api.Assertions.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesDetailsRepositoryImplTest{

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    lateinit var apiService: ApiService


    private lateinit var repositoryImpl : MoviesDetailsRepositoryImpl


    @Before
    fun setup() {

        repositoryImpl = MoviesDetailsRepositoryImpl(apiService)

    }

    @Test
    fun shouldGetMoviesDetailsSuccessResponse() {


        val movieResponse = Mockito.mock(MoviesDetailsResponse::class.java)
        val result1 = Resource.success(movieResponse)

        runBlocking {

            Mockito.doReturn(movieResponse)
                .`when`(apiService)
                .getMoviesDetails(1)

            val response = repositoryImpl.getMoviesDetailsResponse(1).drop(1).first()

            Assert.assertEquals(response, result1)

        }
    }


    @Test
    fun shouldGetMoviesDetailsFailureResponse() {

        val result1 = Resource.error<MoviesDetailsResponse>("NetworkError")


        runBlocking {

            BDDMockito.given(apiService.getMoviesDetails(1)).willAnswer {
                throw IOException("Ooops")
            }

            val response = repositoryImpl.getMoviesDetailsResponse(1).drop(1).first()

            Assert.assertEquals(response, result1)


        }
    }

}