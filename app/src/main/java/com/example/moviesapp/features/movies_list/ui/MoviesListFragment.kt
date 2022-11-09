package com.example.moviesapp.features.movies_list.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesListBinding
import com.example.moviesapp.features.movies_list.viewmodel.MoviesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesListViewModel by viewModels()

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

                val adapter = MovieAdapter{ itemId->
                    Timber.e("ID " + itemId)

                    val bundle = Bundle()
                    bundle.putInt("itemId",itemId)

                    Navigation.findNavController(binding.root).navigate(R.id.action_moviesListFragment_to_moviesDetailsFragment,bundle)

                }

                binding.recyclerViewMain.adapter = adapter
                adapter.submitList(it.data?.results)

            }
        }
    }


}