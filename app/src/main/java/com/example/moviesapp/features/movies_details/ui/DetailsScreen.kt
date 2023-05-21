package com.example.moviesapp.features.movies_details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesapp.features.utils.SetMovieCell
import com.example.moviesapp.features.utils.SetMovieImage
import com.orabi.core.domain.model.movies_details.MoviesDetailsResponse


@Composable
fun DetailsScreen(response: MoviesDetailsResponse?) {

    Surface {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp),
            elevation = 10.dp,

            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SetMovieImage(
                    imageUrl = response?.backdrop_path ?: "", modifier = Modifier
                        .size(300.dp, 150.dp)
                        .clip(RoundedCornerShape(10.dp))
                )


                SetMovieCell(
                    name = "Title",
                    value = response?.title ?: " ali ",
                    modifier = Modifier.height(8.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Overview",
                    value = response?.overview ?: "ahmed mahmoud ali orabi",
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Release Date",
                    value = response?.release_date ?: "ahmed mahmoud ali orabi",
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Original Language",
                    value = response?.original_language ?: "ahmed mahmoud ali orabi",
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Vote Average",
                    value = response?.vote_average.toString(),
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Vote Count",
                    value = response?.vote_count.toString(),
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Tage Line",
                    value = response?.tagline ?: "ahmed mahmoud ali orabi",
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Status",
                    value = response?.status ?: "ahmed mahmoud ali orabi",
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Genres",
                    value = response?.genres.toString(),
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Spoken Language",
                    value = response?.spoken_languages.toString(),
                    modifier = Modifier.height(5.dp)
                )

                SetMovieCell(
                    name = "Production Countries",
                    value = response?.production_countries.toString(),
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Revenue",
                    value = response?.revenue.toString(),
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

                SetMovieCell(
                    name = "Budget",
                    value = response?.budget.toString(),
                    modifier = Modifier.height(5.dp),
                    textAlign = TextAlign.Center
                )

            }
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