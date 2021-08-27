package com.example.movieappmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmvvm.model.MovieResponse
import com.example.movieappmvvm.model.Result
import com.example.movieappmvvm.repository.MovieRepository
import com.example.movieappmvvm.resources.Resource
import com.example.movieappmvvm.util.Utils.Companion.API_KEY
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(val repository: MovieRepository):ViewModel() {

    val popularMovies:MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val popularMoviePage=1

    val topRatedMovies:MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val topRatedMoviePage=1

    val upcomingMovies:MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val upcomingMoviesPage=1


    val searchMovie:MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val searchMoviePage=1

    init {
        getPopularMovies(API_KEY)
        getTopRatedMovies(API_KEY)
        getUpcomingMovies(API_KEY)
    }

     fun getPopularMovies(apiKey:String)=viewModelScope.launch {
         popularMovies.postValue(Resource.Loading())
         val response=repository.getPopularMovie(apiKey,popularMoviePage)
         popularMovies.postValue(handlePopularMovieResponse(response))
     }

    fun getTopRatedMovies(apiKey: String)=viewModelScope.launch {
        topRatedMovies.postValue(Resource.Loading())
        val response=repository.getTopRatedMovie(apiKey,topRatedMoviePage)
        topRatedMovies.postValue(handleTopRatedMovieResponse(response))
    }

    fun getUpcomingMovies(apiKey: String)=viewModelScope.launch {
        upcomingMovies.postValue(Resource.Loading())
        val response=repository.getUpcomingMovie(apiKey,upcomingMoviesPage)
        upcomingMovies.postValue(handleUpcomingMovieResponse(response))
    }

    fun getSearchMovies(apiKey: String,query:String)=viewModelScope.launch {

        searchMovie.postValue(Resource.Loading())
        val response=repository.getMovieSearch(apiKey,query,searchMoviePage)
        searchMovie.postValue(handleSearchNewsResponse(response))
    }


    private fun handlePopularMovieResponse(response: Response<MovieResponse>):Resource<MovieResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleTopRatedMovieResponse(response: Response<MovieResponse>):Resource<MovieResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleUpcomingMovieResponse(response: Response<MovieResponse>):Resource<MovieResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<MovieResponse>) : Resource<MovieResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveResult(result:Result) = viewModelScope.launch {
        repository.insert(result)
    }
    fun getSaved()=repository.getAllMovie()

    fun deleteResult(result:Result) = viewModelScope.launch {
        repository.delete(result)
    }

}