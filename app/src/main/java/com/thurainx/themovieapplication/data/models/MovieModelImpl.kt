package com.thurainx.themovieapplication.data.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.thurainx.themovieapplication.persistence.MovieDatabase
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgent
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl
import com.thurainx.themovieapplication.network.responses.MovieListResponse
import com.thurainx.themovieapplication.utils.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
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

        return mMovieDatabase?.movieDao()?.getMoviesByTypeLiveData(NOW_PLAYING)

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

        return mMovieDatabase?.movieDao()?.getMoviesByTypeLiveData(POPULAR)

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

        return mMovieDatabase?.movieDao()?.getMoviesByTypeLiveData(TOP_RATED)
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

        return mMovieDatabase?.movieDao()?.getMovieByIdLiveData(id.toInt())
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

    override fun searchMovies(query: String): Observable<List<MovieVO>> {
        return mTheMovieApi.searchMovies(query = query)
            .map{ it.results ?: listOf()}
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())

    }

    override fun getNowPlayingMoviesObservable(): Observable<List<MovieVO>>? {
        mTheMovieApi.getNowPlayingMovieList()
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movieList ->
                movieList.forEach {
                    it.type = NOW_PLAYING
                    Log.d("movie",it.originalTitle.toString())
                    mMovieDao?.insertSingleMovie(it)
                }

            }

        return mMovieDao?.getMoviesByTypeFlowable(NOW_PLAYING)?.toObservable()

    }

    override fun getPopularMoviesObservable(): Observable<List<MovieVO>>? {
        mTheMovieApi.getPopularMovieList()
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movieList ->
                movieList.forEach {
                    it.type = POPULAR
                    mMovieDao?.insertSingleMovie(it)
                }
                Log.d("call_api", "i am called.")

            }

        return mMovieDao?.getMoviesByTypeFlowable(POPULAR)?.toObservable()
    }

    override fun getTopRatedMoviesObservable(): Observable<List<MovieVO>>? {
        mTheMovieApi.getTopRatedMovieList()
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movieList ->
               movieList.forEach {
                    it.type = TOP_RATED
                    mMovieDao?.insertSingleMovie(it)
                }
            }

        return mMovieDao?.getMoviesByTypeFlowable(TOP_RATED)?.toObservable()
    }

    override fun getGenresListObservable(): Observable<List<GenreVO>>? {
        return mTheMovieApi.getGenresList()
            .map { it.genres ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
//            .onErrorReturn { throwable ->
//                Log.d("observable_error",throwable.localizedMessage ?: "unknown genreList error")
//                listOf()
//            }
            .subscribeOn(Schedulers.io())
    }

    override fun getMoviesByGeneresObservable(genreId: String): Observable<List<MovieVO>>? {
        return mTheMovieApi.getMoviesByGenre(genreId = genreId)
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
//            .onErrorReturn { throwable ->
//                Log.d("observable_error",throwable.localizedMessage ?: "unknown movieList by genre error")
//                listOf()
//            }
            .subscribeOn(Schedulers.io())
    }

    override fun getActorListObservable(): Observable<List<ActorVO>>? {
        return mTheMovieApi.getActorList()
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())        }

    override fun getMovieDetailByIdObservable(id: String): Observable<MovieVO>? {

        mTheMovieApi.getMovieById(movie_id = id)
            .onErrorComplete()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                val movieFromDBToSync: MovieVO? =
                    mMovieDao?.getOneTimeMovieById(id.toInt())

                    response.type = movieFromDBToSync?.type
                    mMovieDao?.insertSingleMovie(response)

                Log.d("db_movie",response.productionCompanies?.size.toString())

            }

        return mMovieDao?.getMovieByIdFlowable(id.toInt())?.toObservable()
    }

    override fun getCreditByMovieIdObservable(id: String): Observable<Pair<List<ActorVO>, List<ActorVO>>>? {
        return mTheMovieApi.getCreditByMovieId(movieId = id)
            .map { Pair(it.cast ?: listOf(),it.crew ?: listOf()) }
            .onErrorResumeNext { Observable.just(Pair(listOf(), listOf())) }
            .subscribeOn(Schedulers.io())
    }
}