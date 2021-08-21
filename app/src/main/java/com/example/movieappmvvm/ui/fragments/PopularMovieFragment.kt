package com.example.movieappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappmvvm.R
import com.example.movieappmvvm.adapter.MovieResponsAdapter
import com.example.movieappmvvm.resources.Resource
import com.example.movieappmvvm.ui.MainActivity
import com.example.movieappmvvm.util.Utils.Companion.API_KEY
import com.example.movieappmvvm.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_popular_movie.*


class PopularMovieFragment : Fragment(R.layout.fragment_popular_movie) {

    lateinit var viewModel: MovieViewModel
    lateinit var movieAdapter: MovieResponsAdapter
    val TAG = "PopularMovie"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { it ->
                        movieAdapter.differ.submitList(it.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieResponsAdapter()
        rv_popular_movie.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}