package com.example.movieappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.movieappmvvm.R
import com.example.movieappmvvm.adapter.MovieResponsAdapter
import com.example.movieappmvvm.adapter.ViewPagerAdapter
import com.example.movieappmvvm.model.Result
import com.example.movieappmvvm.resources.Resource
import com.example.movieappmvvm.ui.MainActivity
import com.example.movieappmvvm.util.Utils.Companion.API_KEY
import com.example.movieappmvvm.viewmodel.MovieViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_popular_movie.*
import kotlinx.android.synthetic.main.popular_movie_row.*


class PopularMovieFragment : Fragment(R.layout.fragment_popular_movie) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = ViewPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}