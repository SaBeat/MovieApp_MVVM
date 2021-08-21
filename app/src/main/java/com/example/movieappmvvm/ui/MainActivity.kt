package com.example.movieappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieappmvvm.R
import com.example.movieappmvvm.db.MovieDatabase
import com.example.movieappmvvm.repository.MovieRepository
import com.example.movieappmvvm.viewmodel.MovieViewModel
import com.example.movieappmvvm.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = MovieRepository(MovieDatabase.invoke(this))
        val viewModelProviderFactory = MovieViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)
        bottomNavigationView.setupWithNavController(nav_host_fragment_container.findNavController())
    }
}