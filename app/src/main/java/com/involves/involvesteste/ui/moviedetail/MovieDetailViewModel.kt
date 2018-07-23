package com.involves.involvesteste.ui.moviedetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.involves.involvesteste.data.repository.MoviesRepository

class MovieDetailViewModel(
        private val repository: MoviesRepository
) : ViewModel() {

    private val queryLiveData = MutableLiveData<Int>()
//    val movie : LiveData<Movie> = repository.detailMovie()

    fun getMovieDetail(query: Int) {
        queryLiveData.postValue(query)
    }
}