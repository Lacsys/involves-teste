package com.involves.involvesteste.ui.moviedetail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.involves.involvesteste.R
import com.involves.involvesteste.injection.Injection

class MovieDetailFragment : Fragment() {

    private var movieId: Int = -1

    private lateinit var viewModel: MovieDetailViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(it))
                    .get(MovieDetailViewModel::class.java)
        }


//        viewModel.movie.observe(this, Observer {
//            viewModel.getMovieDetail(movieId)
//        })
        viewModel.getMovieDetail(movieId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        arguments?.let {
            movieId = arguments!!.getInt("movie_id")
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        @JvmStatic
        fun newInstance(movieId : Int) = MovieDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt("movie_id", movieId)
                    }
                }
    }
}
