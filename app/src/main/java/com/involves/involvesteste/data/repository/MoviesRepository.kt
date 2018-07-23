package com.involves.involvesteste.data.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import com.involves.involvesteste.data.MoviesBoundaryCallback
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MoviesResult
import com.involves.involvesteste.data.source.local.MoviesLocalCache
import com.involves.involvesteste.data.source.remote.ApiService

class MoviesRepository(
        private val service : ApiService,
        private val moviesCache : MoviesLocalCache
) {

    fun search(query: String): MoviesResult {
        // Get data source factory from the local cache
        val dataSourceFactory = moviesCache.getMoviesDb(query)

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = MoviesBoundaryCallback(query, service, moviesCache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return MoviesResult(data)
    }

    fun detailMovie(movieId : Int) : LiveData<Movie> {
        return moviesCache.getMovieDetailDb(movieId)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }

}
