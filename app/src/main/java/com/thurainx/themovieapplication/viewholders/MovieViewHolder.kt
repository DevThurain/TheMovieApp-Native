package com.thurainx.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.delegates.MovieDelegate
import com.thurainx.themovieapplication.utils.IMAGE_BASED_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import kotlinx.android.synthetic.main.view_item_banner.view.*

class MovieViewHolder(itemView: View,mDelegate:MovieDelegate) : RecyclerView.ViewHolder(itemView) {
    var mMovieVO : MovieVO? = null

    init {
        itemView.setOnClickListener{
            mMovieVO?.let {
                mDelegate.onTapMovie(it.id)
            }
        }
    }

    fun bindData(movie: MovieVO){
        mMovieVO = movie
        itemView.tvMovieName.text = movie.title
        itemView.tvMovieRating.text = movie.voteAverage.toString()
        itemView.rbMovieRating.rating = movie.getVotingBasedOnFiveStars()
        Glide.with(itemView.context)
            .load(IMAGE_BASED_URL.plus(movie.posterPath))
            .into(itemView.ivMovieCover)

    }
}