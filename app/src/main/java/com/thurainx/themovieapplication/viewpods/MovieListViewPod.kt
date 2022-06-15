package com.thurainx.themovieapplication.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.thurainx.themovieapplication.adapters.MovieAdapter
import kotlinx.android.synthetic.main.viewpod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    lateinit var mMovieAdapter: MovieAdapter

    override fun onFinishInflate() {
        mMovieAdapter = MovieAdapter()
        rvMovieList.adapter = mMovieAdapter
        super.onFinishInflate()
    }
}