package com.example.movieappmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieappmvvm.R
import com.example.movieappmvvm.model.Result
import com.example.movieappmvvm.ui.fragments.PopularMovieFragmentDirections
import com.example.movieappmvvm.util.Utils.IMAGE_END_POINT
import kotlinx.android.synthetic.main.popular_movie_row.view.*

class MovieResponsAdapter():RecyclerView.Adapter<MovieResponsAdapter.MyViewHolder>(){

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view)



    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_row,parent,false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val a=differ.currentList[position]
        holder.itemView.text_title.text=a.original_title

        holder.itemView.row_layout.setOnClickListener {
            val direction=PopularMovieFragmentDirections.actionPopularMovieFragmentToDetailFragment(a)
            it.findNavController().navigate(direction)
        }

        Glide.with(holder.itemView.image_popular).load(IMAGE_END_POINT+a.poster_path)
            .centerCrop()
            .into(holder.itemView.image_popular)

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}