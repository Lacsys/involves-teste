package com.involves.involvesteste.ui.movies.adapter

import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.content.Intent
import android.net.Uri
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.involves.involvesteste.R
import com.involves.involvesteste.data.model.Movie
import kotlinx.android.synthetic.main.adapter_movies.view.*

class MoviesAdapter(/*movies : List<Movie>*/) : PagedListAdapter<Movie, RecyclerView.ViewHolder>(MOVIE_COMPARATOR) {

//    private var moviesList = movies as ArrayList<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null)
            (holder as MoviesViewHolder).bindView(movie)
    }

//    fun addMovies(movies : List<Movie>) {
//        this.moviesList.addAll(movies)
//        notifyDataSetChanged()
//    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                    oldItem == newItem
        }
    }
}