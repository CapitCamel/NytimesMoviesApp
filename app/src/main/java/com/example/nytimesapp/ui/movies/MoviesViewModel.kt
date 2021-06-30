package com.example.nytimesapp.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.InvalidatingPagingSourceFactory
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.nytimesapp.data.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MoviesViewModel(private val api: MoviesEndpoints): ViewModel() {

    val movies: StateFlow<PagingData<Results>> =
        Pager(
            PagingConfig(pageSize = 5, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(api) }
        ).flow
            .stateIn(viewModelScope, SharingStarted.Lazily,  PagingData.empty())

}