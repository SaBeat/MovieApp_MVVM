package com.example.movieappmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmvvm.model.MovieResponse
import com.example.movieappmvvm.model.Result
import com.example.movieappmvvm.repository.MovieRepository
import com.example.movieappmvvm.resources.Resource
import com.example.movieappmvvm.util.Utils
import com.example.movieappmvvm.util.Utils.API_KEY
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(val repository: MovieRepository):ViewModel() {

    val popularMovies:MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val topRatedMovies:MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val upcomingMovies:MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val searchMovie:MutableLiveData<Resource<MovieResponse>> = MutableLiveData()


    init {
        getPopularMovies(API_KEY)
        getTopRatedMovies(API_KEY)
        getUpcomingMovies(API_KEY)
    }

     fun getPopularMovies(apiKey:String)=viewModelScope.launch {
         popularMovies.postValue(Resource.Loading())
         val response=repository.getPopularMovie(apiKey, Utils.moviePage)
         popularMovies.postValue(handleMovies(response))
     }

    fun getTopRatedMovies(apiKey: String)=viewModelScope.launch {
        topRatedMovies.postValue(Resource.Loading())
        val response=repository.getTopRatedMovie(apiKey,Utils.moviePage)
        topRatedMovies.postValue(handleMovies(response))
    }

    fun getUpcomingMovies(apiKey: String)=viewModelScope.launch {
        upcomingMovies.postValue(Resource.Loading())
        val response=repository.getUpcomingMovie(apiKey,Utils.moviePage)
        upcomingMovies.postValue(handleMovies(response))
    }

    fun getSearchMovies(apiKey: String,query:String)=viewModelScope.launch {

        searchMovie.postValue(Resource.Loading())
        val response=repository.getMovieSearch(apiKey,query,Utils.moviePage)
        searchMovie.postValue(handleMovies(response))
    }


    private fun handleMovies(response: Response<MovieResponse>):Resource<MovieResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
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