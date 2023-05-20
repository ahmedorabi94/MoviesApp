package com.example.moviesapp.core.domain.model.movies_details

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
){
    override fun toString(): String {
        return name
    }
}