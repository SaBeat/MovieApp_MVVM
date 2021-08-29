package com.example.movieappmvvm.ui.fragments.films

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappmvvm.R
import com.example.movieappmvvm.adapter.MovieResponsAdapter
import com.example.movieappmvvm.resources.Resource
import com.example.movieappmvvm.ui.MainActivity
import com.example.movieappmvvm.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_upcoming.*


class UpcomingFragment : Fragment(R.layout.fragment_upcoming) {


    lateinit var viewModel: MovieViewModel
    lateinit var movieAdapter: MovieResponsAdapter
    val TAG = "Upcoming"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer { response ->
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
        paginationProgressBar_upcoming.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar_upcoming.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieResponsAdapter()
        rv_upcoming.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(activity,2)
        }
    }
}