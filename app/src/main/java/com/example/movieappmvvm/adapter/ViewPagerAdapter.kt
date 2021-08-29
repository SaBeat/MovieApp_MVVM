package com.example.movieappmvvm.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movieappmvvm.R
import com.example.movieappmvvm.ui.fragments.films.UpcomingFragment
import com.example.movieappmvvm.ui.fragments.films.PopularFragment
import com.example.movieappmvvm.ui.fragments.films.TopRatedFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return PopularFragment()
            }
            1 -> {
                return TopRatedFragment()
            }
            2 -> {
                return UpcomingFragment()
            }
            else -> {
                return PopularFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Popular"
            }
            1 -> {
                return "Top Rated"
            }
            2 -> {
                return "Upcoming"
            }
        }
        return super.getPageTitle(position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}