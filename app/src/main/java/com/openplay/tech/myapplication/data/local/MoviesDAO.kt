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
    suspend fun insertAllMovies(entity: MoviesEntity): Long

    @Delete
    suspend fun deleteMovies(entity: MoviesEntity)

    @Query("select * from movies_table")
    fun getAllMovies(): Flow<List<MoviesEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAllMovies(entity: MoviesEntity)

}