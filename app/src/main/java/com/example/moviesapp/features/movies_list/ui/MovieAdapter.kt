package com.example.moviesapp.features.movies_list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.core.domain.model.movies_list.Result
import com.example.moviesapp.databinding.MovieItemBinding
import timber.log.Timber

class MovieAdapter(private val callback: (movieId: Int) -> Unit) :
    ListAdapter<Result, MovieAdapter.MyViewHolder>(
        DiffCallback
    ) {


    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.cardViewItem.setOnClickListener {
            callback.invoke(item.id)


        }

    }


    class MyViewHolder(var binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Result) {
            binding.titleTV.text = item.title
            binding.yearTV.text = item.release_date

            val baseUrl = "http://image.tmdb.org/t/p/w500"
            val finalUrl = baseUrl + item.poster_path

            Timber.e(finalUrl)

            Glide.with(binding.root).load(finalUrl).into(binding.posterIv)

        }

    }


}