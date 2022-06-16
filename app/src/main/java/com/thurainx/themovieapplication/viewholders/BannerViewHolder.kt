package com.thurainx.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.themovieapplication.delegates.BannerDelegate

class BannerViewHolder(itemView: View,private val mDelegate: BannerDelegate) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener{
            mDelegate.onTapBanner()
        }
    }
}