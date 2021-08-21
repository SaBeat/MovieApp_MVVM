package com.example.movieappmvvm.repository

import com.example.movieappmvvm.api.RetrofitClient
import com.example.movieappmvvm.db.MovieDatabase
import com.example.movieappmvvm.model.Result
import retrofit2.Retrofit

class MovieRepository(val db: MovieDatabase) {

    suspend fun getPopularMovies(apiKey:String,page: Int) = RetrofitClient.getApi
        .getPopularMovie(apiKey,page)

    suspend fun getMovieSearch(apiKey: String,query:String,page:Int)=RetrofitClient.getApi
        .getMovieSearch(apiKey,query,page)

    suspend fun insert(result: Result)=db.movieDao().insert(result)

    fun getAllMovie()=db.movieDao().getAllMovies()

    suspend fun delete(result: Result)=db.movieDao().delete(result)
}