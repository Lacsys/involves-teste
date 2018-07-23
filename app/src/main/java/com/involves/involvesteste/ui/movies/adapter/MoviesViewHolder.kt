package com.involves.involvesteste.ui.movies.adapter

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.involves.involvesteste.R
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.ui.moviedetail.MovieDetailActivity
import com.involves.involvesteste.utils.Constants
import kotlinx.android.synthetic.main.adapter_movies.view.*

class MoviesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private var movie : Movie? = null

    init {

    }

    fun bindView(movie : Movie) {
        val titleMovie = itemView.txt_title_movie
        val releaseDate = itemView.txt_release_date
        val imgPoster = itemView.img_poster

        titleMovie.text = movie.title
        releaseDate.text = movie.releaseDate

        Glide.with(itemView.context)
                .load(Constants.IMG_BASE_URL + movie.posterPath)
                .apply(RequestOptions.centerCropTransform())
                .into(imgPoster)

        itemView.setOnClickListener {
            movie.title?.let { title ->
                val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                intent.putExtra("movie_id", movie.id)
                itemView.context.startActivity(intent)
                Log.d("MoviesAdapter", title)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MoviesViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_movies, parent, false)
            return MoviesViewHolder(view)
        }
    }
}