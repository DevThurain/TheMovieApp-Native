package com.thurainx.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.themovieapplication.delegates.MovieDelegate

class MovieViewHolder(itemView: View,mDelegate:MovieDelegate) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener{
            mDelegate.onTapMovie()
        }
    }
}