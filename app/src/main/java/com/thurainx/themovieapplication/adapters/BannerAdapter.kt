package com.thurainx.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.delegates.BannerDelegate
import com.thurainx.themovieapplication.viewholders.BannerViewHolder

class BannerAdapter(val delegate: BannerDelegate) : RecyclerView.Adapter<BannerViewHolder>(){

    private var mMovieList: List<MovieVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_banner,parent,false)

        return BannerViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.onBind(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        if(mMovieList.size > 5){
            return 5
        }else{
            return mMovieList.size
        }
    }

    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}