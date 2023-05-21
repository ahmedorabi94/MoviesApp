package com.example.moviesapp.features.movies_list.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orabi.core.domain.model.movies_list.Result
import com.example.moviesapp.features.utils.SetMovieCell
import com.example.moviesapp.features.utils.SetMovieImage
import com.example.moviesapp.features.utils.ShowMySnackBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieCard(item: Result? = null, onClick: () -> Unit, onLongClick: () -> Unit) {

    var isLongClick by remember { mutableStateOf(false) }

    if (isLongClick) {
        ShowMySnackBar()
        //SnackbarDemo()
        //onLongClick()
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = {
                    isLongClick = true
                },
            ),
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
    ) {

        Row(modifier = Modifier.padding(15.dp)) {

            SetMovieImage(
                imageUrl = item?.poster_path ?: "", Modifier
                    .size(100.dp, 150.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(
                modifier = Modifier.padding(15.dp),
                verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
            ) {
                SetMovieCell(
                    name = "Title",
                    value = item?.title ?: "ahmed mahmoud ali orabi",
                    modifier = Modifier.height(1.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                SetMovieCell(
                    name = "Release Date",
                    value = item?.release_date ?: "",
                    modifier = Modifier.height(1.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                SetMovieCell(
                    name = "Original Language",
                    value = item?.original_language ?: "",
                    modifier = Modifier.height(1.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                SetMovieCell(
                    name = "Vote Average",
                    value = item?.vote_average.toString(),
                    modifier = Modifier.height(1.dp)
                )

            }
        }


    }
}


@Preview
@Composable
private fun MoviePreview() {
    MaterialTheme {
        MovieCard(onClick = {}, onLongClick = {})
    }
}