package com.involves.involvesteste

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.involves.involvesteste.movies.MoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, MoviesFragment.newInstance())
                .commit()
    }
}
