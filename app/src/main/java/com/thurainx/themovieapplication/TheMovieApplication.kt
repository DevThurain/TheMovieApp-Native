package com.thurainx.themovieapplication

import android.app.Application
import com.thurainx.themovieapplication.data.models.MovieModelImpl

class TheMovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MovieModelImpl.initDatabase(applicationContext)
    }
}