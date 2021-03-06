package com.thurainx.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.delegates.BannerDelegate
import com.thurainx.themovieapplication.viewholders.BannerViewHolder

class BannerAdapter(val delegate: BannerDelegate) : RecyclerView.Adapter<BannerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_banner,parent,false)

        return BannerViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 4
    }
}