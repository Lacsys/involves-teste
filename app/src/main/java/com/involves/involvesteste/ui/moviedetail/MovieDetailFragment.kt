package com.involves.involvesteste.ui.moviedetail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.involves.involvesteste.R
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.injection.Injection
import com.involves.involvesteste.utils.Constants
import com.involves.involvesteste.utils.Utils
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    private lateinit var movie: Movie

    private lateinit var viewModel: MovieDetailViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(it))
                    .get(MovieDetailViewModel::class.java)
        }

        viewModel.getMovieDetail(movie.id).observe(this, Observer {
            toolbar.title = it?.title
            Glide.with(this)
                    .load(Constants.IMG_BASE_URL+movie.backdropPath)
                    .apply(RequestOptions().centerCrop())
                    .into(appbar_movie_poster)
            txt_release_date.text = " - "
            txt_description.text = " - "

            it?.releaseDate?.let {
                txt_release_date.text = String.format(getString(R.string.release_date),
                        Utils.formatDate("yyyy-MM-dd", "dd/MM/yyyy", it))
            }
            it?.tagline?.let {
                txt_description.text = it
            }

            val genresString = " - "
            it?.genres?.let {
                for(genre in it) {
                    genresString.plus(", ").plus(genre.name)
                }
            }

            txt_genre.text = genresString

            txt_overview.text = it?.overview

        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        arguments?.let {
            movie = arguments!!.getParcelable("movie")
        }

        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(movie : Movie) = MovieDetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("movie", movie)
                    }
                }
    }
}
