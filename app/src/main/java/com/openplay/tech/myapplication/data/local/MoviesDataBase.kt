package com.openplay.tech.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MoviesEntity::class], version = 1)
abstract class MoviesDataBase: RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDAO
}

