package com.example.movieappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieappmvvm.db.MovieDatabase
import com.example.movieappmvvm.repository.MovieRepository
import com.example.movieappmvvm.viewmodel.MovieViewModel
import com.example.movieappmvvm.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import androidx.navigation.ui.NavigationUI

import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.movieappmvvm.R.layout.activity_main)

        val newsRepository = MovieRepository(MovieDatabase.invoke(this))
        val viewModelProviderFactory = MovieViewModelFactory(newsRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)
        val navHostFragment = supportFragmentManager.findFragmentById(com.example.movieappmvvm.R.id.nav_host_fragment_container) as NavHostFragment?
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment!!.navController)


    }

}