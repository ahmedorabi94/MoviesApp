package com.example.moviesapp.features.movies_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.moviesapp.R
import com.example.moviesapp.core.data.api.Resource
import com.example.moviesapp.databinding.FragmentMoviesListBinding
import com.example.moviesapp.features.movies_list.viewmodel.MoviesListViewModel
import dagger.hilt.android.AndroidEntryPoint

const val MOVIE_ID_KEY = "movie_id"

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesListViewModel by viewModels()

    private val adapter = MovieAdapter { itemId ->
        val bundle = Bundle()
        bundle.putInt(MOVIE_ID_KEY, itemId)

        Navigation.findNavController(binding.root)
            .navigate(R.id.action_moviesListFragment_to_moviesDetailsFragment, bundle)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.moviesResponse.observe(viewLifecycleOwner) { moviesListResponse ->
            moviesListResponse?.let {

                when (it.status) {
                    Resource.Status.LOADING -> {
                        showLoading()
                    }
                    Resource.Status.SUCCESS -> {
                        hideLoading()
                        binding.recyclerViewMain.adapter = adapter
                        adapter.submitList(it.data?.results)
                    }
                    Resource.Status.ERROR -> {
                        hideLoading()
                        Toast.makeText(activity, moviesListResponse.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }


            }
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