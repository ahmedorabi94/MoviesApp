package com.example.moviesapp.features.movies_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.TextFieldValue
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_list.Result
import com.example.moviesapp.features.movies_list.viewmodel.MoviesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

const val MOVIE_ID_KEY = "movie_id"

@AndroidEntryPoint
class MoviesListFragment : Fragment() {


    private val viewModel: MoviesListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                MyApp()

            }
        }
    }

    @Composable
    fun MyApp() {
        Scaffold(
            topBar = {
                TopAppBar {
                    val textState = remember { mutableStateOf(TextFieldValue("")) }
                    SearchAppBar(modifier = Modifier.fillMaxWidth(), state = textState, onQueryChange = {
                        Timber.e("onTriggerNextPage" + it)
                        viewModel.getSearchMoviesListFlow(it)
                    }, onExecuteSearch = {
                        Timber.e("onExecuteSearch")
                    })
                }
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                ObserveGenresListViewModel()
                ObserveMovieListViewModel()
            }
        }


    }


    @Composable
    fun SetMovieList(movieList: List<Result>) {

        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(movieList) { movie ->
                MovieCard(movie) {
                    val bundle = Bundle()
                    bundle.putInt(MOVIE_ID_KEY, movie.id)

                    findNavController(requireView())
                        .navigate(R.id.action_moviesListFragment_to_moviesDetailsFragment, bundle)

                }
            }
        }

    }

    @Composable
    fun ObserveMovieListViewModel() {

        val response = viewModel.moviesResponse.observeAsState()

        when (response.value?.status) {
            Resource.Status.LOADING -> {
                ShowLoading()
            }

            Resource.Status.SUCCESS -> {
                SetMovieList(movieList = response.value?.data?.results!!)
            }

            Resource.Status.ERROR -> {
                Toast.makeText(activity, response.value?.message, Toast.LENGTH_LONG)
                    .show()
            }

            else -> {}
        }


    }

    @Composable
    fun ObserveGenresListViewModel() {

        val response = viewModel.genresResponse.observeAsState()

        when (response.value?.status) {
            Resource.Status.LOADING -> {
                ShowLoading()
            }

            Resource.Status.SUCCESS -> {
                ChipGroup(genres = response.value?.data?.genres!!, onSelectedChanged = {
                    Timber.e("Genre " + it)
                    viewModel.getMoviesListFlow(it.toString())
                })
            }

            Resource.Status.ERROR -> {
                Toast.makeText(activity, response.value?.message, Toast.LENGTH_LONG)
                    .show()
            }

            else -> {}
        }


    }

    @Composable
    private fun ShowLoading() {


        Box(
            contentAlignment = Alignment.Center, // you apply alignment to all children
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center) // or to a specific child
            )
        }
    }


}