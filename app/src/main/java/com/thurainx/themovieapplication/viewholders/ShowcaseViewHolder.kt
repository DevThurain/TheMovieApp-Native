package com.thurainx.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.delegates.ShowcaseDelegate
import com.thurainx.themovieapplication.utils.IMAGE_BASED_URL
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import kotlinx.android.synthetic.main.view_holder_showcase.view.*

class ShowcaseViewHolder(itemView: View, private val mDelegate : ShowcaseDelegate) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener{
            mDelegate.onTapShowcase()
        }
    }

    fun bindData(movie: MovieVO){
        itemView.tvShowcaseName.text = movie.title
        itemView.tvShowcaseDate.text = movie.releaseDate.toString()
        Glide.with(itemView.context)
            .load(IMAGE_BASED_URL.plus(movie.posterPath))
            .into(itemView.ivShowCase)

    }
}