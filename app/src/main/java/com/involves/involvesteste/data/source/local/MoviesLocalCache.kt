package com.involves.involvesteste.data.source.local

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.involves.involvesteste.data.model.Movie
import java.util.concurrent.Executor

class MoviesLocalCache(
        private val moviesDao: MoviesDao,
        private val ioExecutor: Executor
) {

    fun insert(movies: List<Movie>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            moviesDao.insertMovies(movies)
            insertFinished()
        }
    }

    fun getMoviesDb(name: String): DataSource.Factory<Int, Movie> {
        return moviesDao.queryUpcomingMoviesDb()
    }

    fun getMovieDetailDb(movieId: Int): LiveData<Movie> {
        return moviesDao.queryDetailMovieDb(movieId)
    }
}