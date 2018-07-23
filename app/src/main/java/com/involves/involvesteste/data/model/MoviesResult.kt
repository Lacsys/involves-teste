package com.involves.involvesteste.data.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "movies")
data class Movie(
        @PrimaryKey
        val id : Int,
        @SerializedName("poster_path") val posterPath : String?,
        @SerializedName("overview") val overview : String?,
        @SerializedName("release_date") val releaseDate : String?,
        @SerializedName("original_title") val originalTitle : String?,
        @SerializedName("title") val title : String?,
        @SerializedName("backdrop_path") val backdropPath : String?,
        @SerializedName("genre_ids") val genreIds: List<Int>?

)

data class MoviesResponse(
        val page : Int,
        val results : List<Movie>,
        @SerializedName("total_pages") val total : Int
)

data class MoviesResult(
        val moviesData: LiveData<PagedList<Movie>>
)

data class MovieDetail(
        val id : Int,
        @SerializedName("poster_path") val posterPath : String?,
        val overview : String?,
        @SerializedName("release_date") val releaseDate : Date?,
        val genres : List<Genre>?
)

data class Genre(
        val id : Int,
        val name : String?
)