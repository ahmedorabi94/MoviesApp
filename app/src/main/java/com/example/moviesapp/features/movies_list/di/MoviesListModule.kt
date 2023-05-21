package com.example.moviesapp.features.movies_list.di


import com.orabi.core.data.api.ApiService
import com.orabi.core.repo.movies_details.MoviesDetailsDataSource
import com.orabi.core.repo.movies_list.MoviesListDataSource
import com.example.moviesapp.features.movies_details.framework.MoviesDetailsRepositoryImpl
import com.example.moviesapp.features.movies_list.framework.MoviesListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesListModule {


    @Singleton
    @Provides
     fun provideMoviesListRepositoryImpl(apiService: ApiService): MoviesListDataSource {
        return MoviesListRepositoryImpl(apiService)
    }
    @Singleton
    @Provides
    fun provideMoviesDetailsRepositoryImpl(apiService: ApiService): MoviesDetailsDataSource {
        return MoviesDetailsRepositoryImpl(apiService)
    }


}