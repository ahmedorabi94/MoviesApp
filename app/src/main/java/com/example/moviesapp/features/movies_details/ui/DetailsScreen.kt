package com.example.moviesapp.features.movies_details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import com.example.moviesapp.core.domain.model.movies_details.MoviesDetailsResponse


@Composable
fun DetailsScreen(response: MoviesDetailsResponse?) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(15.dp),
        elevation = 10.dp,

        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val baseUrl = "http://image.tmdb.org/t/p/w500"
            val finalUrl = baseUrl + response?.backdrop_path


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(finalUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(300.dp, 150.dp)
                    .clip(RoundedCornerShape(10.dp)),


                )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = ("Title : " + response?.title)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Overview : " + response?.overview)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Release Date : " + response?.release_date)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Original Language : " + response?.original_language)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Vote Average : " + response?.vote_average)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Vote Count : " + response?.vote_count)
            )

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Tage Line : " + response?.tagline)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Status : " + response?.status)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Genres : " + response?.genres.toString())
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Spoken Language : " + response?.spoken_languages.toString())
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Production Countries : " + response?.production_countries.toString())
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Revenue : " + response?.revenue)
            )

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = ("Budget : " + response?.budget)
            )
        }
    }


}


@Preview
@Composable
private fun MoviePreview() {
    MaterialTheme {
        DetailsScreen(null)
    }
}