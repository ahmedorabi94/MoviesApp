package com.example.moviesapp.features.movies_list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.core.domain.model.movies_list.Result


@Composable
fun MovieCard(item: Result? = null, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable(onClick = onClick),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
    ) {

        val baseUrl = "http://image.tmdb.org/t/p/w500"
        val finalUrl = baseUrl + item?.poster_path

        Row(modifier = Modifier.padding(15.dp)) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(finalUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp, 150.dp)
                    .clip(RoundedCornerShape(10.dp)),


                )

            Column(
                modifier = Modifier.padding(15.dp),
                verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
            ) {
                SetMovieCell(name = "Title", value = item?.title ?: "ahmed mahmoud ali orabi")
                Spacer(modifier = Modifier.height(2.dp))
                SetMovieCell(name = "Release Date", value = item?.release_date ?: "")
                Spacer(modifier = Modifier.height(2.dp))
                SetMovieCell(name = "Original Language", value = item?.original_language ?: "")
                Spacer(modifier = Modifier.height(2.dp))
                SetMovieCell(name = "Vote Average", value = item?.vote_average.toString())

            }
        }


    }
}

@Composable
fun SetMovieCell(name : String , value : String){
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))) {
                append("$name : ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.W600, color = Color(0xFF4552B8))) {
                append(value)
            }
        }
    )
}

@Preview
@Composable
private fun MoviePreview() {
    MaterialTheme {
        MovieCard(onClick = {})
    }
}