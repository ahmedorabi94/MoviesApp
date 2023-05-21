package com.example.moviesapp.core.data.api

import com.example.moviesapp.core.domain.model.movies_details.MoviesDetailsResponse
import com.example.moviesapp.core.domain.model.movies_list.MoviesListResponse
import com.example.moviesapp.features.movies_list.models.GenresResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getMoviesListAsync(
        @Query("with_genres") with_genres: String = "28",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("query") query: String = ""
    ): MoviesListResponse

    @GET("genre/movie/list?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getGenresListAsync(): GenresResponse

    @GET("search/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getSearchMovieAsync(
        @Query("query") query: String, @Query("with_genres") with_genres: String = "28",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): MoviesListResponse


    @GET("movie/{id}?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    suspend fun getMoviesDetails(@Path("id") id: Int): MoviesDetailsResponse

}