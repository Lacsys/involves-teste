package com.involves.involvesteste.ui.movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MoviesResult
import com.involves.involvesteste.data.repository.MoviesRepository

class MoviesViewModel(
        private val repository : MoviesRepository
) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()
    private val movieResult: LiveData<MoviesResult> = Transformations.map(queryLiveData, {
        repository.search(it)
    })

    val movies: LiveData<PagedList<Movie>> = Transformations.switchMap(movieResult,
            { it -> it.moviesData
            })

    fun searchMovies(queryString: String) {
        queryLiveData.postValue(queryString)
    }
}