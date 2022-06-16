package com.thurainx.themovieapplication.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thurainx.themovieapplication.R

class MovieDetailActivity : AppCompatActivity() {

    companion object{
        fun newIntent(context: Context) : Intent{
            val intent = Intent(context,MovieDetailActivity::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
    }
}