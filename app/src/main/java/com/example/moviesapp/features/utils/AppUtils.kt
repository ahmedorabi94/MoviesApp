package com.example.moviesapp.features.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R


const val BASE_URL = "https://api.themoviedb.org/3/"


@Composable
fun SetMovieImage(imageUrl: String, modifier: Modifier) {
    val baseUrl = "http://image.tmdb.org/t/p/w500"
    val finalUrl = baseUrl + imageUrl

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(finalUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_background),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}


@Composable
fun SetMovieCell(name: String, value: String , modifier: Modifier , textAlign: TextAlign = TextAlign.Start) {
    Spacer(modifier = modifier)
    Text(textAlign = textAlign,
       text =  buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))) {
                append("$name : ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.W600, color = Color(0xFF4552B8))) {
                append(value)
            }
        }
    )
}