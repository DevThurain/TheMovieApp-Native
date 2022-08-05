package com.thurainx.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.delegates.ShowcaseDelegate
import com.thurainx.themovieapplication.viewholders.ShowcaseViewHolder

class ShowcaseAdapter(val delegate: ShowcaseDelegate) : RecyclerView.Adapter<ShowcaseViewHolder>(){
    private var mMovieList: List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_showcase,parent,false)

        return ShowcaseViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {
        holder.bindData(mMovieList[position])
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
