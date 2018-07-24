package com.involves.involvesteste.data.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

data class MoviesResponse(
        val page : Int,
        val results : List<Movie>,
        @SerializedName("total_pages") val total : Int
)

data class MoviesResult(
        val moviesData: LiveData<PagedList<Movie>>
)

@Entity(tableName = "movies_detail")
data class MovieDetail(
        @PrimaryKey
        val id : Int,
        @SerializedName("original_title") val originalTitle : String?,
        @SerializedName("title") val title : String?,
        @SerializedName("poster_path") val posterPath : String?,
        val overview : String?,
        @SerializedName("release_date") val releaseDate : String?,
        val genres : List<Genre>?,
        val tagline : String?
)