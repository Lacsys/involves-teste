package com.involves.involvesteste.data.source.remote

import android.util.Log
import com.involves.involvesteste.data.model.Genre
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MovieDetail
import com.involves.involvesteste.data.model.MoviesResponse
import com.involves.involvesteste.utils.Constants
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") page : Int, @Query("language") language : String) : Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId : Int) : Call<MovieDetail>

    @GET("genre/movie/list")
    fun getGenresList() : Observable<Response<List<Genre>>>
}

fun searchMovies(
        service: ApiService,
        query: String,
        page: Int,
        itemsPerPage: Int,
        onSuccess: (repos: List<Movie>) -> Unit,
        onError: (error: String) -> Unit) {
        Log.d("ApiService", "query: $query, page: $page, itemsPerPage: $itemsPerPage")

    service.getUpcomingMovies(page, Constants.LANGUAGE).enqueue(
            object : Callback<MoviesResponse> {
                override fun onFailure(call: Call<MoviesResponse>?, t: Throwable) {
//                    Log.d(TAG, "fail to get data")
                    onError(t.message ?: "unknown error")
                }

                override fun onResponse(
                        call: Call<MoviesResponse>?,
                        response: Response<MoviesResponse>
                ) {
//                    Log.d(TAG, "got a response $response")
                    if (response.isSuccessful) {
                        val movies = response.body()?.results ?: emptyList()
                        onSuccess(movies)
                    } else {
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }
            }
    )
}

fun getMovieForId(
        service: ApiService,
        movieId: Int,
        onSuccess: (repos: MovieDetail?) -> Unit,
        onError: (error: String) -> Unit) {

    service.getDetailMovie(movieId)
            .enqueue(
                    object : Callback<MovieDetail> {
                        override fun onFailure(call: Call<MovieDetail>?, t: Throwable) {
                            onError(t.message ?: "unknown error")
                        }

                        override fun onResponse(call: Call<MovieDetail>?, response: Response<MovieDetail>) {
                            if (response.isSuccessful) {
                                val movie = response.body()
                                onSuccess(movie)
                            } else {
                                onError(response.errorBody()?.string() ?: "Unknown error")
                            }
                        }
                    }
            )
        }