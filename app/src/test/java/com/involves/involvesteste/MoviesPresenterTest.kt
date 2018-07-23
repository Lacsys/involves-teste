package com.involves.involvesteste

import com.involves.involvesteste.data.model.Movie
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class MoviesPresenterTest {


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun fetchValidDataShouldLoadIntoView() {
        val m = Movie(0, null, null, null, null, null)

    }
}