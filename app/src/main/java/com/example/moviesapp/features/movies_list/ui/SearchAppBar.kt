package com.example.moviesapp.features.movies_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesapp.features.movies_details.ui.DetailsScreen


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchAppBar(
    state: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    onQueryChange: (String) -> Unit = {},
    onExecuteSearch: () -> Unit = {},
    modifier: Modifier = Modifier.fillMaxWidth()
) {

    Surface(
        modifier = modifier,
        elevation = 8.dp
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
            Box(
                modifier = modifier
            ) {
                TextField(
                    value = state.value,onValueChange = { value ->
                        state.value = value
                        onQueryChange(state.value.text)
                    },
                    label = { Text(text = "Search...") },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch()
                            keyboardController?.hide()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface) ,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = MaterialTheme.colors.surface)
                )

            }


    }
}

@Preview
@Composable
private fun PlantNamePreview() {
    MaterialTheme {
        SearchAppBar()
    }
}