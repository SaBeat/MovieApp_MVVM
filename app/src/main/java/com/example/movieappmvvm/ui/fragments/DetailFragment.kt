package com.example.movieappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.example.movieappmvvm.R
import com.example.movieappmvvm.model.Result
import com.example.movieappmvvm.ui.MainActivity
import com.example.movieappmvvm.util.Utils.Companion.IMAGE_END_POINT
import com.example.movieappmvvm.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var result:Result
    lateinit var viewModel: MovieViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel

        result=args.now
        val image_backdrop=result.backdrop_path
        val image_poster=result.poster_path
        val title=result.original_title
        val overview=result.overview
        val releaseDate=result.release_date
        val voteAverege=result.vote_average
        val popularity=result.popularity


        Glide.with(this).load(IMAGE_END_POINT+image_backdrop).into(image_detail_backdrop)
        Glide.with(this).load(IMAGE_END_POINT+image_poster).into(image_detail_poster)
        text_details_popularity.text="Popularity: "+popularity.toString()
        text_details_releasedate.text="Release date: "+releaseDate
        text_details_vote.text="Vote averege: "+voteAverege.toString()
        text_detail_title.text=title
        text_detail_overview.text=overview

        btn_fab.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        btn_fab.setOnClickListener {
            viewModel.saveResult(result)
            Snackbar.make(view, "Movie saved successfully", Snackbar.LENGTH_SHORT).show()
            btn_fab.setImageResource(R.drawable.ic_baseline_favorite_24)
        }

        back_detail_image.setOnClickListener {
            val direction=DetailFragmentDirections.actionDetailFragmentToPopularMovieFragment()
            findNavController().navigate(direction)
        }

    }
}