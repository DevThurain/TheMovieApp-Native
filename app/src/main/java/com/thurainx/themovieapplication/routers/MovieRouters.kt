package com.thurainx.themovieapplication.routers

import android.app.Activity
import android.content.Intent
import com.thurainx.themovieapplication.activities.MovieDetailActivity
import com.thurainx.themovieapplication.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailActivity(movieId: Int){
    startActivity(MovieDetailActivity.newIntent(context = this, movieId = movieId))
}

fun Activity.navigateToMovieSearchActivity(){
    startActivity(MovieSearchActivity.newIntent(this))
}