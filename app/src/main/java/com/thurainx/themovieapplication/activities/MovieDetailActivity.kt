package com.thurainx.themovieapplication.activities

import android.app.Person
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.viewpods.PersonListViewPod
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    companion object{
        fun newIntent(context: Context) : Intent{
            val intent = Intent(context,MovieDetailActivity::class.java)
            return intent
        }
    }

    lateinit var mActorListViewPod: PersonListViewPod
    lateinit var mCreatorListViewPod: PersonListViewPod
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupViewPods()
        setupListeners()
    }

    private fun setupListeners() {
        flBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setupViewPods() {
        mActorListViewPod = vpMovieDetailActorList as PersonListViewPod
        mCreatorListViewPod = vpMovieDetailCreatorList as PersonListViewPod

        mActorListViewPod.setupPersonViewpod(
            backgroundColorReference = ContextCompat.getColor(this,R.color.colorPrimary),
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )
        mCreatorListViewPod.setupPersonViewpod(
            backgroundColorReference = ContextCompat.getColor(this,R.color.colorPrimary),
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }
}