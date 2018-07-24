package com.involves.involvesteste.ui.moviedetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.involves.involvesteste.data.model.MovieDetail
import com.involves.involvesteste.data.repository.MoviesRepository

class MovieDetailViewModel(
        private val repository: MoviesRepository
) : ViewModel() {

    private val queryLiveData = MutableLiveData<Int>()

    fun getMovieDetail(id: Int) : LiveData<MovieDetail> {
        queryLiveData.value = id
        queryLiveData.postValue(id)
        return repository.detailMovieFromId(id)
    }
}