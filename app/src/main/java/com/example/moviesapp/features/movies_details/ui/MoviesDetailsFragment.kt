package com.example.moviesapp.features.movies_details.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.FragmentMoviesDetailsBinding
import com.example.moviesapp.features.movies_details.viewmodel.MoviesDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MoviesDetailsFragment : Fragment() {
    private var itemId: Int? = null
    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesDetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getInt("itemId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        Timber.e("Here Id " + itemId)


        viewModel.getMoviesDetailsResponse(itemId ?: 0)

        viewModel.moviesResponse.observe(viewLifecycleOwner){
            it?.let {

                Timber.e(it.data.toString())

                binding.tileTv.text = it.data?.title
                binding.releaseDateTv.text = it.data?.release_date
                binding.overviewTv.text = it.data?.overview


                val baseUrl = "http://image.tmdb.org/t/p/w500"
                val finalUrl = baseUrl + it.data?.poster_path
                Glide.with(binding.root).load(finalUrl).into(binding.posterIv)
            }
        }


        return binding.root

    }


}