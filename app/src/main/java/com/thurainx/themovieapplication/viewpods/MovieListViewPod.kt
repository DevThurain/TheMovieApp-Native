package com.thurainx.themovieapplication.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.thurainx.themovieapplication.adapters.MovieAdapter
import com.thurainx.themovieapplication.delegates.MovieDelegate
import kotlinx.android.synthetic.main.viewpod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    lateinit var mMovieAdapter: MovieAdapter
    lateinit var mDelegate : MovieDelegate

    fun setUpMovieListViewPod(delegate: MovieDelegate){
        setDelegate(delegate)
        setUpMovieRecyclerView()
    }

    private fun setDelegate(delegate: MovieDelegate){
        mDelegate = delegate
    }

    private fun setUpMovieRecyclerView(){
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }
}