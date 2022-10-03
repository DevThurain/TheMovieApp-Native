package com.thurainx.themovieapplication.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO

class MainViewModel : ViewModel() {
    // model
    val mMovieModel = MovieModelImpl

    // livedata
    var nowPlayingMovieLiveData: LiveData<List<MovieVO>>? = null
    var popularMovieLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMovieLiveData: LiveData<List<MovieVO>>? = null
    val genreLiveData = MutableLiveData<List<GenreVO>>()
    val movieLiveDataByGenre = MutableLiveData<List<MovieVO>>()
    val actorLiveData = MutableLiveData<List<ActorVO>>()
    val errorLiveData = MutableLiveData<String>()

    init {
        nowPlayingMovieLiveData = mMovieModel.getNowPlayingMovies { errorLiveData.postValue(it) }
        popularMovieLiveData = mMovieModel.getPopularMovies { errorLiveData.postValue(it) }
        topRatedMovieLiveData = mMovieModel.getTopRatedMovies { errorLiveData.postValue(it) }

        mMovieModel.getGenresList(
            onSuccess = { genresList ->
                genreLiveData.postValue(genresList)
                genresList.firstOrNull()?.let {
                    getMoviesByGenre(position = 0)
                }
            },
            onFail = {
                errorLiveData.postValue(it)
            }
        )

        mMovieModel.getActorList(
            onSuccess = { actorList ->
                actorLiveData.postValue(actorList)
            },
            onFail = {
                errorLiveData.postValue(it)
            }
        )

    }

     fun getMoviesByGenre(position: Int) {
        genreLiveData.value?.getOrNull(position)?.let { genreVO ->
            mMovieModel.getMoviesByGeneres(
                genreId = genreVO.id.toString(),
                onSuccess = { movieList ->
                    movieLiveDataByGenre.postValue(movieList)
                },
                onFail = {
                    errorLiveData.postValue(it)
                }
            )
        }

    }
}