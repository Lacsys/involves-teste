package com.involves.involvesteste.ui.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.involves.involvesteste.R
import com.involves.involvesteste.base.BaseFragment
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.injection.Injection
import com.involves.involvesteste.ui.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : BaseFragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var viewModel : MoviesViewModel
    val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        context?.let {
            viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(it))
                    .get(MoviesViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = recyclerView_movies
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        configAdapter()

        viewModel.searchMovies("")

    }

    private fun configAdapter() {
        recyclerView.adapter = adapter
        viewModel.movies.observe(this, Observer<PagedList<Movie>> {
            adapter.submitList(it)
            showLoading(it?.size == 0)
        })
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            progress.visibility = VISIBLE
        } else {
            progress.visibility = GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                MoviesFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
