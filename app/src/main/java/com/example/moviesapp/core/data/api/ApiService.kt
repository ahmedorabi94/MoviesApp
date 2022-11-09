package com.example.moviesapp.core.data.api

import com.example.moviesapp.core.domain.model.movies_details.MoviesDetailsResponse
import com.example.moviesapp.core.domain.model.movies_list.MoviesListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getMoviesListAsync(): MoviesListResponse


    @GET("movie/{id}?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getMoviesDetails(@Path("id") id: Int): MoviesDetailsResponse

}