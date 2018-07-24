package com.involves.involvesteste.data.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.util.Log
import com.involves.involvesteste.data.MoviesBoundaryCallback
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MovieDetail
import com.involves.involvesteste.data.model.MoviesResult
import com.involves.involvesteste.data.source.local.MoviesLocalCache
import com.involves.involvesteste.data.source.remote.ApiService
import com.involves.involvesteste.data.source.remote.getMovieForId
import com.involves.involvesteste.utils.Utils

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

    fun detailMovieFromId(movieId : Int) : LiveData<MovieDetail> {
        val hasConnection = Utils.isConnectedToInternet()
        if (hasConnection) {
            getMovieForId(service, movieId, {
                it?.let {movieDetail ->
                    moviesCache.insertMovie(movieDetail, {

                    })
                }
            }, {
                    error -> Log.d("MoviesRepository", error)
            })
        }
        return moviesCache.getMovieDetailDb(movieId)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }

}
