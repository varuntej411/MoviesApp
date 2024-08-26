package com.openplay.tech.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
@TypeConverters(DataTypeConverter::class)
abstract class MoviesDataBase: RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDAO
}
