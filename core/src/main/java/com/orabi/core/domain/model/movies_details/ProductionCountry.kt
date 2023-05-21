package com.orabi.core.domain.model.movies_details

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
){
    override fun toString(): String {
        return name
    }
}