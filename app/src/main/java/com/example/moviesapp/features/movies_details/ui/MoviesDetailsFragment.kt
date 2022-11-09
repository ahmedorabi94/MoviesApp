package com.example.moviesapp.features.movies_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.core.domain.model.movies_details.MoviesDetailsResponse
import com.example.moviesapp.databinding.FragmentMoviesDetailsBinding
import com.example.moviesapp.features.movies_details.viewmodel.MoviesDetailsViewModel
import com.example.moviesapp.features.movies_list.ui.MOVIE_ID_KEY
import com.example.moviesapp.features.utils.setPoster
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailsFragment : Fragment() {
    private var itemId: Int? = null
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
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
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)

        viewModel.getMoviesDetailsResponse(itemId ?: 0)

        viewModel.moviesResponse.observe(viewLifecycleOwner) {
            it?.let {

                when (it.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                    }
                    Resource.Status.SUCCESS -> {
                        hideLoading()
                        updateUI(it.data)
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                }


            }
        }


        return binding.root

    }

    private fun updateUI(response: MoviesDetailsResponse?) {
        response?.let {
            binding.tileTv.text = response.title
            binding.releaseDateTv.text = response.release_date
            binding.overviewTv.text = response.overview

            binding.posterIv.setPoster(response.poster_path)

        }

    }

    private fun showLoading() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressbar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}