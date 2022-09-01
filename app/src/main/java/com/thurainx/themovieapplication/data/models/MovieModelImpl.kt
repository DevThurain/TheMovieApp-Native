package com.thurainx.themovieapplication.data.models

import android.content.Context
import com.thurainx.themovieapplication.persistence.MovieDatabase
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgent
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl
import com.thurainx.themovieapplication.utils.*

object MovieModelImpl : MovieModel {
    /* DataAgent */
    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    /* Database */
    var mMovieDatabase: MovieDatabase? = null

    fun initDatabase(context: Context) {
        mMovieDatabase = MovieDatabase.getDBInstant(context)
    }

    override fun getNowPlayingMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(NOW_PLAYING) ?: listOf())

        mMovieDataAgent.getNowPlayingMovies(
            onSuccess = { movieList ->
                movieList
                movieList.forEach {
                    it.type = NOW_PLAYING
                    mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                }
                onSuccess(movieList)

            }, onFail = onFail
        )
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(POPULAR) ?: listOf())

        mMovieDataAgent.getPopularMovies(
            onSuccess = { movieList ->
                movieList.forEach {
                    it.type = POPULAR
                    mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                }
                onSuccess(movieList)

            }, onFail = onFail
        )
    }

    override fun getTopRatedMovies(onSuccess: (List<MovieVO>) -> Unit, onFail: (String) -> Unit) {
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(TOP_RATED) ?: listOf())

        mMovieDataAgent.getTopRatedMovies(
            onSuccess = { movieList ->
                movieList.forEach {
                    it.type = TOP_RATED
                    mMovieDatabase?.movieDao()?.insertSingleMovie(it)
                }
                onSuccess(movieList)


            }, onFail = onFail
        )
    }

    override fun getGenresList(onSuccess: (List<GenreVO>) -> Unit, onFail: (String) -> Unit) {
        mMovieDataAgent.getGenresList(onSuccess = onSuccess, onFail = onFail)
    }

    override fun getMoviesByGeneres(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFail: (String) -> Unit
    ) {
        mMovieDataAgent.getMoviesByGenre(genreId = genreId, onSuccess = onSuccess, onFail = onFail)
    }

    override fun getActorList(onSuccess: (List<ActorVO>) -> Unit, onFail: (String) -> Unit) {
        onSuccess(mMovieDatabase?.actorDao()?.getPopularActorList() ?: listOf())
        mMovieDataAgent.getActorList(
            onSuccess = { actorList ->
                actorList.forEach {
                    it.type = POPULAR
                    mMovieDatabase?.actorDao()?.insertSingleActor(it)
                }
                onSuccess(actorList)
            }, onFail = onFail
        )
    }

    override fun getMovieDetailById(
        id: String,
        onSuccess: (MovieVO) -> Unit,
        onFail: (String) -> Unit
    ) {
        val mMovie: MovieVO? = mMovieDatabase?.movieDao()?.getMovieById(id.toInt())
        mMovie?.let {
            onSuccess(it)
        }

        mMovieDataAgent.getMovieDetailById(id = id, onSuccess = { movie ->
            mMovie?.let {
                movie.type = it.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(movie)
            }
            onSuccess(movie)

        }, onFail = onFail)
    }

    override fun getCreditByMovieId(
        id: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFail: (String) -> Unit
    ) {
        val castList : List<ActorVO> = mMovieDatabase?.actorDao()?.getActorByType(id = id, type = CAST) ?: listOf()
        val crewList : List<ActorVO> = mMovieDatabase?.actorDao()?.getActorByType(id = id, type = CREW) ?: listOf()
        onSuccess(Pair(castList,crewList))

        mMovieDataAgent.getCreditByMovieId(
            id = id,
            onSuccess = {
                 it.first.forEach { actor ->
                     actor.movieId = id
                     actor.type = CAST
                     mMovieDatabase?.actorDao()?.insertSingleActor(actor)
                 }
                it.second.forEach { actor ->
                    actor.movieId = id
                    actor.type = CREW
                    mMovieDatabase?.actorDao()?.insertSingleActor(actor)
                }
                onSuccess(it)
            },
            onFail = onFail)
    }
}