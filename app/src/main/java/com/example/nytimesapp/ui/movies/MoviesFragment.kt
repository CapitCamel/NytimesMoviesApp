package com.example.nytimesapp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.viewmodel.ext.android.viewModel
//import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nytimesapp.MoviesAdapter
import com.example.nytimesapp.databinding.FragmentMoviesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<MoviesViewModel>()

    var rvAdapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val rvLayoutManager = GridLayoutManager(context, 1)

        binding.recyclerView.apply {
            layoutManager = rvLayoutManager
            adapter = rvAdapter.withLoadStateFooter(MoviesLoaderStateAdapter())
        }

        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                rvAdapter.submitData(it)
            }
        }

        return binding.root
    }

}