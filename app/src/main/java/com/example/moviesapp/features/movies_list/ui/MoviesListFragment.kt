package com.example.moviesapp.features.movies_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.moviesapp.R
import com.example.moviesapp.features.movies_list.viewmodel.MoviesListViewModel
import com.example.moviesapp.features.utils.MovieSearch
import com.orabi.core.data.api.Resource
import com.orabi.core.domain.model.movies_list.Result
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
                    SearchAppBar(state = textState, onQueryChange = {
                        Timber.e("onTriggerNextPage$it")
                        viewModel.isSearch = true
                        viewModel.movieSearch.value =
                            MovieSearch(viewModel.movieSearch.value.genre, it)

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
    fun ObserveMovieListViewModel() {
        val movies2 = viewModel.items.collectAsLazyPagingItems()

        SetMovieList(movieList = movies2)


    }

    @Composable
    fun SetMovieList(movieList: LazyPagingItems<Result>) {

        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = movieList) { movie ->

                movie?.let {
                    MovieCard(it, onClick = {
                        val bundle = Bundle()
                        bundle.putInt(MOVIE_ID_KEY, it.id)

                        findNavController(requireView())
                            .navigate(
                                R.id.action_moviesListFragment_to_moviesDetailsFragment,
                                bundle
                            )
                    })
                }


            }


            when (val state = movieList.loadState.refresh) { //FIRST LOAD
                is LoadState.Error -> {
                    Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_LONG).show()
                }

                is LoadState.Loading -> { // Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Refresh Loading"
                            )

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }

                else -> {}
            }


            when (val state = movieList.loadState.append) { // Pagination
                is LoadState.Error -> {
                    Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_LONG).show()
                }

                is LoadState.Loading -> { // Pagination Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Pagination Loading")

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }

                else -> {}
            }

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
                    Timber.e("Genre $it")
                    viewModel.isSearch = false
                    viewModel.movieSearch.value =
                        MovieSearch(it.toString(), viewModel.movieSearch.value.query)


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