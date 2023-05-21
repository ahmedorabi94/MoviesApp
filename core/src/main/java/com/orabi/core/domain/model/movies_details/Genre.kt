package com.orabi.core.domain.model.movies_details

data class Genre(
    val id: Int,
    val name: String
){
    override fun toString(): String {
        return name
    }
}