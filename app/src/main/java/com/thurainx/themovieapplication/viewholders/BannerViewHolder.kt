package com.thurainx.themovieapplication.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.delegates.BannerDelegate
import com.thurainx.themovieapplication.utils.BASED_URL
import com.thurainx.themovieapplication.utils.IMAGE_BASED_URL
import kotlinx.android.synthetic.main.view_item_banner.view.*

class BannerViewHolder(itemView: View,private val mDelegate: BannerDelegate) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener{
            mDelegate.onTapBanner()
        }
    }

    fun onBind(movie: MovieVO){
        itemView.tvBannerName.text = movie.title
        Glide.with(itemView.context)
            .load(IMAGE_BASED_URL.plus(movie.posterPath))
            .into(itemView.ivBannerImage)

        Log.d("posterPath",BASED_URL.plus(movie.posterPath))

    }
}