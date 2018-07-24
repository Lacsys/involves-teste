package com.involves.involvesteste.ui.moviedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.involves.involvesteste.R
import com.involves.involvesteste.data.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getParcelableExtra<Movie>("movie")

        supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, MovieDetailFragment.newInstance(movie))
                .commit()
    }
}
