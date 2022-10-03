package com.thurainx.themovieapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.MovieVO

class MovieDetailViewModel : ViewModel() {
    // model
    val mMovieModel = MovieModelImpl

    // liveData
    var movieDetailLiveData: LiveData<MovieVO>? = null
    val castAndCrewLiveData = MutableLiveData<Pair<List<ActorVO>, List<ActorVO>>>()
    val errorLiveData = MutableLiveData<String>()

    fun initViewModel(movieId: Int) {
        movieDetailLiveData = mMovieModel.getMovieDetailById(id = movieId.toString()) {
            errorLiveData.postValue(it)
        }

        mMovieModel.getCreditByMovieId(
            id = movieId.toString(),
            onSuccess = {
                castAndCrewLiveData.postValue(it)
            },
            onFail = {
                errorLiveData.postValue(it)
            }
        )
    }
}