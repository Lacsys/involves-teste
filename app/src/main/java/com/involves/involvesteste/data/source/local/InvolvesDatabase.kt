package com.involves.involvesteste.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.involves.involvesteste.data.model.Converters
import com.involves.involvesteste.data.model.Movie
import com.involves.involvesteste.data.model.MovieDetail

@Database(entities = [(Movie::class), (MovieDetail::class)], version = 1)
@TypeConverters(Converters::class)
abstract class InvolvesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}