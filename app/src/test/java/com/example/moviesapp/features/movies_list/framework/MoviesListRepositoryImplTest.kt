package com.example.moviesapp.features.movies_list.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapp.TestCoroutineRule
import com.example.moviesapp.features.movies_details.framework.MoviesDetailsRepositoryImpl
import com.orabi.core.data.api.ApiService
import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.genres_list.GenresResponse
import com.orabi.core.domain.model.movies_details.MoviesDetailsResponse
import org.junit.jupiter.api.Assertions.*
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
 class MoviesListRepositoryImplTest{


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    lateinit var apiService: ApiService


    private lateinit var repositoryImpl : MoviesListRepositoryImpl


    @Before
    fun setup() {

        repositoryImpl = MoviesListRepositoryImpl(apiService)

    }


    @Test
    fun shouldGetGenresResponseSuccessResponse() {


        val movieResponse = Mockito.mock(GenresResponse::class.java)
        val result1 = Resource.success(movieResponse)

        runBlocking {

            Mockito.doReturn(movieResponse)
                .`when`(apiService)
                .getGenresListAsync()

            val response = repositoryImpl.getGenresListResponse().drop(1).first()

            Assert.assertEquals(response, result1)

        }
    }


    @Test
    fun shouldGetGenresResponseFailureResponse() {

        val result1 = Resource.error<GenresResponse>("NetworkError")


        runBlocking {

            BDDMockito.given(apiService.getGenresListAsync()).willAnswer {
                throw IOException("Ooops")
            }

            val response = repositoryImpl.getGenresListResponse().drop(1).first()

            Assert.assertEquals(response, result1)


        }
    }

 }