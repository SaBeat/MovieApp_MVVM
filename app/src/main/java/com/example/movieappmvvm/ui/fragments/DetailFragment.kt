package com.example.movieappmvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.example.movieappmvvm.R
import com.example.movieappmvvm.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var result:Result
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        result=args.now
        val image_backdrop=result.backdrop_path
        val image_poster=result.poster_path
        val title=result.original_title
        val overview=result.overview
        val releaseDate=result.release_date
        val voteAverege=result.vote_average
        val popularity=result.popularity


        Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+image_backdrop).into(image_detail_backdrop)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+image_poster).into(image_detail_poster)
        text_details_popularity.text="Popularity: "+popularity.toString()
        text_details_releasedate.text="Release date: "+releaseDate
        text_details_vote.text="Vote averege: "+voteAverege.toString()
        text_detail_title.text=title
        text_detail_overview.text=overview
    }
}