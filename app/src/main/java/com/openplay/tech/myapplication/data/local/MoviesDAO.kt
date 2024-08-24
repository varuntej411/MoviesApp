package com.openplay.tech.myapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllmovies(entity: MoviesEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllmovies(entity: MoviesEntity)

    @Delete
    suspend fun deletemovies(entity: MoviesEntity)

    @Query("Select * from movies_table")
    fun getAllmovies(): List<MoviesEntity>

    @Query("select * from movies_table WHERE city = :city")
    fun getMoviesByCity(city: String): List<MoviesEntity>

    @Query("SELECT * FROM movies_table WHERE state = :area")
    fun getMoviesByArea(area: String): List<MoviesEntity>

    @Query("SELECT *  FROM movies_table ORDER BY city ASC")
    fun getMoviesSortASC(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movies_table ORDER BY city DESC")
    fun getMoviesSortDSC(): Flow<List<MoviesEntity>>

    @Query("DELETE  FROM movies_table ")
    fun deleteAllMoviesList()
}