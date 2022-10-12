package com.thurainx.themovieapplication.activities

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
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
import com.thurainx.themovieapplication.mvi.intent.MovieDetailIntent
import com.thurainx.themovieapplication.mvi.mviBased.MVIView
import com.thurainx.themovieapplication.mvi.state.MovieDetailState
import com.thurainx.themovieapplication.mvi.viewmodel.MainViewModel
import com.thurainx.themovieapplication.mvi.viewmodel.MovieDetailViewModel
import com.thurainx.themovieapplication.utils.IMAGE_BASED_URL
import com.thurainx.themovieapplication.viewpods.PersonListViewPod
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.view_item_banner.view.*

val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

class MovieDetailActivity : AppCompatActivity(), MVIView<MovieDetailState> {

    companion object {
        fun newIntent(context: Context, movieId: Int?): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }

    lateinit var mActorListViewPod: PersonListViewPod
    lateinit var mCreatorListViewPod: PersonListViewPod

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupViewModel()

        setupListeners()
        initViewPods()

        setupInitialIntent()
        observeState()
    }

    private fun setupViewModel(){
        movieDetailViewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
    }

    private fun setupInitialIntent(){
        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieDetailViewModel.processIntent(intent = MovieDetailIntent.getMovieDetailByMovieId(id = movieId), owner = this)
    }

    private fun observeState(){
        movieDetailViewModel.state.observe(this, this::render)
    }

    private fun bindData(movie: MovieVO) {
        tvMovieDetailName.text = movie.title
        collapsingToolbar.title = movie.title
        tvVoteCount.text = movie.voteCount.toString().plus(" Voted")
        tvRatingValue.text = movie.voteAverage.toString()
        tvReleaseYear.text = movie.releaseDate?.substring(0, 4)
        Glide.with(this)
            .load(IMAGE_BASED_URL.plus(movie.posterPath))
            .into(ivMovieDetail)
        tvPlot.text = movie.overview.toString()
        tvTitleText.text = movie.title
        tvTypeText.text = movie.getGenreListString()
        tvProductionText.text = movie.getCountryListString()
        tvDescriptionText.text = movie.overview
        bindGenres(movie.genres ?: listOf())
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
            super.onBackPressed()
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

    private fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_SHORT).show()
    }

    override fun render(state: MovieDetailState) {
        if(state.errorMessage.isNotEmpty()){
            showError(state.errorMessage)
        }

        state.movieData?.let {
            bindData(movie = it)
        }

        bindViewPods(castList = state.castList, creatorList = state.crewList)
    }

}