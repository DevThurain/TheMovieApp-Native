package com.thurainx.themovieapplication.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.adapters.MovieAdapter
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.delegates.MovieDelegate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_search.*
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(),MovieDelegate {
    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MovieSearchActivity::class.java)
            return intent
        }
    }
    lateinit var mMovieAdapter: MovieAdapter
    var mMovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        setupEditText()
        setupRecyclerView()
    }

    private fun setupEditText(){
        edtSearch.textChanges()
            .debounce(500L,TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovies(it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)

            },{
                Snackbar.make(window.decorView,it.localizedMessage ?: "Unknown Search Error",Snackbar.LENGTH_SHORT).show()
            })
    }

    private fun setupRecyclerView(){
        mMovieAdapter = MovieAdapter(this)
        rvSearch.adapter = mMovieAdapter
    }

    override fun onTapMovie(movieId: Int?) {

    }
}