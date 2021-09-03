package com.example.movieappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.movieappmvvm.R
import com.example.movieappmvvm.adapter.ViewPagerAdapter

import kotlinx.android.synthetic.main.fragment_popular_movie.*


class PopularMovieFragment : Fragment(R.layout.fragment_popular_movie) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = ViewPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}