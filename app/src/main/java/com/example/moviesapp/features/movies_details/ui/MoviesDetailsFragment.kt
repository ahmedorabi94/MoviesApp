package com.example.moviesapp.features.movies_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviesapp.features.movies_details.viewmodel.MoviesDetailsViewModel
import com.example.moviesapp.features.movies_list.ui.MOVIE_ID_KEY
import com.orabi.core.data.api.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailsFragment : Fragment() {

    private var itemId: Int? = null
    private val viewModel: MoviesDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getInt(MOVIE_ID_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                viewModel.getMoviesDetailsResponse(itemId ?: 0)
                UpdateUI()
            }
        }
    }


    @Composable private fun UpdateUI() {

        val response = viewModel.moviesResponse.observeAsState()

        when (response.value?.status) {
            Resource.Status.LOADING -> {
                ShowLoading()
            }

            Resource.Status.SUCCESS -> {
                DetailsScreen(response = response.value?.data)
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