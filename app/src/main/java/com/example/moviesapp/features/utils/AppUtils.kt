package com.example.moviesapp.features.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

const val NETWORK_PAGE_SIZE = 20

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

@Composable
fun ShowMySnackBar(movieName : String = "") {
    Column {
        val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

        Button(onClick = { setSnackBarState(!snackbarVisibleState) }) {
            if (snackbarVisibleState) {
                Text("Hide Snackbar")
            } else {
                Text("Show Snackbar")
            }
        }
        if (snackbarVisibleState) {
            Snackbar(

                action = {
//                    Button(onClick = {}) {
//                        Text("MyAction")
//                    }
                },
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "This is a snackbar!") }
        }
    }
}

//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun SnackbarDemo() {
//    val scaffoldState: ScaffoldState = rememberScaffoldState()
//    val coroutineScope: CoroutineScope = rememberCoroutineScope()
//
//    Scaffold(scaffoldState = scaffoldState) {
//        Button(onClick = {
//            coroutineScope.launch {
//                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
//                    message = "This is your message",
//                    actionLabel = "Do something"
//                )
//                when (snackbarResult) {
//                    SnackbarResult.Dismissed -> {}
//                    SnackbarResult.ActionPerformed -> {
//
//                    }
//                }
//            }
//        }) {
//            Text(text = "Click me!")
//        }
//    }
//}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SnackbarDemo() {
    // decouple snackbar host state from scaffold state for demo purposes
// this state, channel and flow is for demo purposes to demonstrate business logic layer
    val snackbarHostState = remember { SnackbarHostState() }
// we allow only one snackbar to be in the queue here, hence conflated
    val channel = remember { Channel<Int>(Channel.CONFLATED) }
    LaunchedEffect(channel) {
        channel.receiveAsFlow().collect { index ->
            val result = snackbarHostState.showSnackbar(
                message = "Snackbar # $index",
                actionLabel = "Action on $index"
            )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    /* action has been performed */
                }
                SnackbarResult.Dismissed -> {
                    /* dismissed, no action needed */
                }
            }
        }
    }
    Scaffold(
        // attach snackbar host state to the scaffold
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        floatingActionButton = {
            var clickCount by remember { mutableStateOf(0) }
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                onClick = {
                    // offset snackbar data to the business logic
                    channel.trySend(++clickCount)
                }
            )
        },
        content = { innerPadding ->
            Text(
                "Snackbar demo",
                modifier = Modifier.padding(innerPadding).fillMaxSize().wrapContentSize()
            )
        }
    )
}