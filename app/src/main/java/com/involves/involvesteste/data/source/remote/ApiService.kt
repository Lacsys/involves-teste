package com.involves.involvesteste.data.source.remote

import android.util.Log
import com.involves.involvesteste.data.model.Genre
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MoviesResponse
import com.involves.involvesteste.data.model.MoviesResult
import com.involves.involvesteste.utils.Constants
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
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
    fun getDetailMovie(@Path("movie_id") movieId : Int) : Observable<Response<Movie>>

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

//    val apiQuery = query + IN_QUALIFIER

//    service.getUpcomingMovies(page, Constants.LANGUAGE)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                    {result ->
//                        onSuccess(result.results)
////                        moviesView?.onFetchDataSuccess(result)
//                    },
//                    {error ->
////                        moviesView?.onFetchDataError(error)
//                        onError(error.message ?: "Unknown")
//                    }
//            )
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