package com.example.moviesapp.features.utils

data class MovieSearch(
    val genre: String = "28",
    val query: String = ""
){


    fun copyWithGenre(myGenre: String) : MovieSearch {
        //uses the fields name and property defined in the constructor
        return MovieSearch(myGenre,query)
    }

    fun copyWithQuery(myQuery: String) : MovieSearch {
        //uses the fields name and property defined in the constructor
        return MovieSearch(genre,myQuery)
    }

}