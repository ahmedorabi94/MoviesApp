package com.example.moviesapp.features.utils

import android.widget.ImageView
import com.bumptech.glide.Glide


const val BASE_URL = "https://api.themoviedb.org/3/"

fun ImageView.setPoster(url : String){
    val baseUrl = "http://image.tmdb.org/t/p/w500"

    val finalUrl = baseUrl + url

    Glide.with(this.context).load(finalUrl).into(this)
}