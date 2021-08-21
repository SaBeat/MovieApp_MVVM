package com.example.movieappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieappmvvm.model.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result: Result)

    @Query("SELECT * FROM result_table")
    fun getAllMovies():LiveData<List<Result>>

    @Delete
    suspend fun delete(result: Result)
}