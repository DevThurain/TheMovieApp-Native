package com.thurainx.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.themovieapplication.delegates.ShowcaseDelegate

class ShowcaseViewHolder(itemView: View, private val mDelegate : ShowcaseDelegate) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener{
            mDelegate.onTapShowcase()
        }
    }
}