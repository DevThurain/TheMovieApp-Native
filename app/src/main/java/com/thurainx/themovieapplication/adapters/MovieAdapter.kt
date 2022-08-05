package com.thurainx.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.delegates.MovieDelegate
import com.thurainx.themovieapplication.viewholders.MovieViewHolder

class MovieAdapter(val delegate: MovieDelegate) : RecyclerView.Adapter<MovieViewHolder>(){
    var mMovieList : List<MovieVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie,parent,false)

        return MovieViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
       return mMovieList.size
    }

    fun setNewData(movieList: List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}