package com.example.nytimesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytimesapp.data.Results
//import com.example.nytimesapp.data.MovieData
import com.example.nytimesapp.databinding.ItemBinding

class MoviesAdapter : PagingDataAdapter<Results, MoviesViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.display_title == newItem.display_title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }
}

class MoviesViewHolder(private val binding: ItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Results) {
        Glide.with(itemView.context).load(movie.multimedia.src).into(binding.image)
        binding.title.text = movie.display_title
        binding.description.text = movie.summary_short
    }
}