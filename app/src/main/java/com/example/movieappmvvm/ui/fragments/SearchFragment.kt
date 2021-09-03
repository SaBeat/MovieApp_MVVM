package com.example.movieappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.movieappmvvm.R
import com.example.movieappmvvm.adapter.SearchAdapter
import com.example.movieappmvvm.resources.Resource
import com.example.movieappmvvm.ui.MainActivity
import com.example.movieappmvvm.util.Utils.API_KEY
import com.example.movieappmvvm.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment(R.layout.fragment_search) {
    lateinit var viewModel: MovieViewModel
    lateinit var movieResponsAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()


        var job:Job?=null
        etSearch.addTextChangedListener {
            job?.cancel()
            job= MainScope().launch {
                delay(500L)
                it.let {
                    if(it.toString().isNotEmpty()){
                        viewModel.getSearchMovies(API_KEY,it.toString())
                    }
                }
            }

        }
        viewModel.searchMovie.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        movieResponsAdapter.differ.submitList(newsResponse.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("TAG", "An error occured: $message")
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
        movieResponsAdapter = SearchAdapter()
        rvSearchNews.apply {
            adapter = movieResponsAdapter
            layoutManager = StaggeredGridLayoutManager(
                2, // span count
                StaggeredGridLayoutManager.VERTICAL
            )
        }
    }

}