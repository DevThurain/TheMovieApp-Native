package com.thurainx.themovieapplication.data.models

import android.content.Context
import androidx.lifecycle.LiveData
import com.thurainx.themovieapplication.persistence.MovieDatabase
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgent
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl
import com.thurainx.themovieapplication.utils.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BasedModel(), MovieModel {
//    /* DataAgent */
//    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl
//
//    /* Database */
//    var mMovieDatabase: MovieDatabase? = null
//
//    fun initDatabase(context: Context) {
//        mMovieDatabase = MovieDatabase.getDBInstant(context)
//    }

    override fun getNowPlayingMovies(onFail: (String) -> Unit): LiveData<List<MovieVO>>? {

        mTheMovieApi.getNowPlayingMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results?.forEach {
                    it.type = NOW_PLAYING
                    mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                }
            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })

        return mMovieDatabase?.movieDao()?.getMoviesByType(NOW_PLAYING)

    }

    override fun getPopularMovies(onFail: (String) -> Unit): LiveData<List<MovieVO>>? {

        mTheMovieApi.getPopularMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results?.forEach {
                    it.type = POPULAR
                    mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                }
            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })

        return mMovieDatabase?.movieDao()?.getMoviesByType(POPULAR)

    }

    override fun getTopRatedMovies(onFail: (String) -> Unit): LiveData<List<MovieVO>>? {

        mTheMovieApi.getTopRatedMovieList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results?.forEach {
                    it.type = TOP_RATED
                    mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                }
            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })

        return mMovieDatabase?.movieDao()?.getMoviesByType(TOP_RATED)
    }

    override fun getGenresList(onSuccess: (List<GenreVO>) -> Unit, onFail: (String) -> Unit) {
        mTheMovieApi.getGenresList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                onSuccess(response.genres ?: listOf())
            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })
    }

    override fun getMoviesByGeneres(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFail: (String) -> Unit
    ) {
        mTheMovieApi.getMoviesByGenre(genreId = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                onSuccess(response.results ?: listOf())
            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })
    }

    override fun getActorList(onSuccess: (List<ActorVO>) -> Unit, onFail: (String) -> Unit) {
        onSuccess(mMovieDatabase?.actorDao()?.getPopularActorList() ?: listOf())

        mTheMovieApi.getActorList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results?.forEach {
                    it.type = POPULAR
                    mMovieDatabase?.actorDao()?.insertSingleActor(it)
                }
                onSuccess(response.results ?: listOf())
            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })
    }

    override fun getMovieDetailById(
        id: String,
        onFail: (String) -> Unit
    ): LiveData<MovieVO>? {


        mTheMovieApi.getMovieById(movie_id = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val movieFromDBToSync: MovieVO? =
                    mMovieDatabase?.movieDao()?.getOneTimeMovieById(id.toInt())

                movieFromDBToSync?.let {
                    response.type = it.type
                    mMovieDatabase?.movieDao()?.insertSingleMovie(response)
                }
            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })

        return mMovieDatabase?.movieDao()?.getMovieById(id.toInt())
    }

    override fun getCreditByMovieId(
        id: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFail: (String) -> Unit
    ) {
        val castList: List<ActorVO> =
            mMovieDatabase?.actorDao()?.getActorByType(id = id, type = CAST) ?: listOf()
        val crewList: List<ActorVO> =
            mMovieDatabase?.actorDao()?.getActorByType(id = id, type = CREW) ?: listOf()
        onSuccess(Pair(castList, crewList))

        mTheMovieApi.getCreditByMovieId(movieId = id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                response.cast?.forEach { actor ->
                    actor.movieId = id
                    actor.type = CAST
                    mMovieDatabase?.actorDao()?.insertSingleActor(actor)
                }

                response.crew?.forEach { actor ->
                    actor.movieId = id
                    actor.type = CREW
                    mMovieDatabase?.actorDao()?.insertSingleActor(actor)
                }

                onSuccess(Pair(response.cast ?: listOf(), response.crew ?: listOf()))

            }, {
                onFail(it.localizedMessage ?: "unknown error")
            })

    }
}