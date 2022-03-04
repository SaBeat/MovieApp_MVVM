package com.example.movieappmvvm.repository

import com.example.movieappmvvm.data.api.RetrofitClient
import com.example.movieappmvvm.data.api.db.MovieDatabase
import com.example.movieappmvvm.data.api.model.Result

class MovieRepository(val db: MovieDatabase) {

    suspend fun getPopularMovie(apiKey:String,page: Int) = RetrofitClient.getApi
        .getPopularMovie(apiKey,page)

    suspend fun getTopRatedMovie(apiKey: String,page: Int)=RetrofitClient.getApi
        .getTopRatedMovie(apiKey,page)

    suspend fun getUpcomingMovie(apiKey: String,page: Int)=RetrofitClient.getApi
        .getUpcomingMovie(apiKey,page)

    suspend fun getMovieSearch(apiKey: String,query:String,page:Int)=RetrofitClient.getApi
        .getMovieSearch(apiKey,query,page)

    suspend fun insert(result: Result)=db.movieDao().insert(result)

    fun getAllMovie()=db.movieDao().getAllMovies()

    suspend fun delete(result: Result)=db.movieDao().delete(result)
}