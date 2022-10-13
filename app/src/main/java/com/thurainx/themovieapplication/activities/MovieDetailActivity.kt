package com.thurainx.themovieapplication.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.data.models.MovieModel
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.mvp.presenters.MovieDetailPresenter
import com.thurainx.themovieapplication.mvp.presenters.MovieDetailPresenterImpl
import com.thurainx.themovieapplication.mvp.views.MovieDetailView
import com.thurainx.themovieapplication.utils.IMAGE_BASED_URL
import com.thurainx.themovieapplication.viewpods.PersonListViewPod
import kotlinx.android.synthetic.main.activity_movie_detail.*

val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {

    companion object {
        fun newIntent(context: Context, movieId: Int?): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    lateinit var mActorListViewPod: PersonListViewPod
    lateinit var mCreatorListViewPod: PersonListViewPod

    lateinit var movieDetailPresenter: MovieDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupPresenter()

        setupListeners()
        initViewPods()

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieDetailPresenter.onUiReady(movieId = movieId, owner = this)
    }

    private fun setupPresenter(){
        movieDetailPresenter = ViewModelProvider(this)[MovieDetailPresenterImpl::class.java]
        movieDetailPresenter.initView(this)
    }


    override fun showData(movieDetail: MovieVO?) {
        tvMovieDetailName.text = movieDetail?.title
        collapsingToolbar.title = movieDetail?.title
        tvVoteCount.text = movieDetail?.voteCount.toString().plus(" Voted")
        tvRatingValue.text = movieDetail?.voteAverage.toString()
        tvReleaseYear.text = movieDetail?.releaseDate?.substring(0, 4)
        Glide.with(this)
            .load(IMAGE_BASED_URL.plus(movieDetail?.posterPath))
            .into(ivMovieDetail)
        tvPlot.text = movieDetail?.overview.toString()
        tvTitleText.text = movieDetail?.title
        tvTypeText.text = movieDetail?.getGenreListString()
        tvProductionText.text = movieDetail?.getCountryListString()
        tvDescriptionText.text = movieDetail?.overview
        bindGenres(movieDetail?.genres ?: listOf())
    }

    override fun showCastAndCrew(cast: List<ActorVO>, crew: List<ActorVO>) {
        bindViewPods(castList = cast, creatorList = crew)
    }

    override fun navigateBack() {
        super.onBackPressed()
    }

    private fun bindGenres(genres: List<GenreVO>) {
        if (genres.size >= 1) {
            genreFirst.text = genres[0].name
            genreFirst.visibility = View.VISIBLE
        }
        if (genres.size >= 2) {
            genreSecond.text = genres[1].name
            genreSecond.visibility = View.VISIBLE
        }
        if (genres.size >= 3) {
            genreThird.text = genres[2].name
            genreThird.visibility = View.VISIBLE
        }
    }

    private fun setupListeners() {
        flBack.setOnClickListener {
            movieDetailPresenter.onTapBack()
        }
    }

    private fun initViewPods() {
        mActorListViewPod = vpMovieDetailActorList as PersonListViewPod
        mCreatorListViewPod = vpMovieDetailCreatorList as PersonListViewPod

        mActorListViewPod.setupPersonViewpod(
            backgroundColorReference = ContextCompat.getColor(this, R.color.colorPrimary),
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )
        mCreatorListViewPod.setupPersonViewpod(
            backgroundColorReference = ContextCompat.getColor(this, R.color.colorPrimary),
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }

    private fun bindViewPods(castList: List<ActorVO>, creatorList: List<ActorVO>) {
        mActorListViewPod.setData(castList)
        mCreatorListViewPod.setData(creatorList)

        if (castList.isEmpty()) {
            mActorListViewPod.visibility = View.GONE
        } else {
            mActorListViewPod.visibility = View.VISIBLE
        }

        if (creatorList.isEmpty()) {
            mCreatorListViewPod.visibility = View.GONE
        } else {
            mCreatorListViewPod.visibility = View.VISIBLE
        }

    }

    override fun showError(msg: String) {
        Snackbar.make(window.decorView, msg, Snackbar.LENGTH_SHORT).show()
    }

}