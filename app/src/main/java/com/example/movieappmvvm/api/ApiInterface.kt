package com.example.movieappmvvm.api

import com.example.movieappmvvm.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("3/movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>


    @GET("3/search/movie")
   suspend fun getMovieSearch(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page:Int
    ): Response<MovieResponse>
}