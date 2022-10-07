package com.thurainx.themovieapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.adapters.BannerAdapter
import com.thurainx.themovieapplication.adapters.ShowcaseAdapter
import com.thurainx.themovieapplication.data.models.MovieModel
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.data.vos.MovieVO
import com.thurainx.themovieapplication.delegates.BannerDelegate
import com.thurainx.themovieapplication.delegates.MovieDelegate
import com.thurainx.themovieapplication.delegates.ShowcaseDelegate
import com.thurainx.themovieapplication.dummy.dummyGeneresList
import com.thurainx.themovieapplication.mvp.presenters.MainPresenter
import com.thurainx.themovieapplication.mvp.presenters.MainPresenterImpl
import com.thurainx.themovieapplication.mvp.views.MainView
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgentImpl
import com.thurainx.themovieapplication.network.dataagents.OkhttpDataAgentImpl
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl
import com.thurainx.themovieapplication.routers.navigateToMovieDetailActivity
import com.thurainx.themovieapplication.routers.navigateToMovieSearchActivity
import com.thurainx.themovieapplication.viewpods.MovieListViewPod
import com.thurainx.themovieapplication.viewpods.PersonListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    // adapters
    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowcaseAdapter: ShowcaseAdapter

    // viewpods
    private lateinit var mBestAndPopularMovieListViewPod: MovieListViewPod
    private lateinit var mGeneresMovieListViewPod: MovieListViewPod
    private lateinit var mActorListViewPod: PersonListViewPod

    // presenter
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPresenter()

        setupActionBar()
        setupBannerViewPager()
        setupViewPods()
        setupListener()
        setupShowcase()

        mainPresenter.onUiReady(this)


    }

    private fun setupPresenter(){
        mainPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mainPresenter.initView(this)
        showMessage("Viper Architecture")
    }


    private fun setupActionBar() {
        setSupportActionBar(toolBar)
        toolBar.setNavigationIcon(R.drawable.ic_menu)
    }

    private fun setupBannerViewPager() {
        mBannerAdapter = BannerAdapter(mainPresenter)
        viewPagerBanner.adapter = mBannerAdapter
        dotsIndicatorBanner.attachTo(viewPagerBanner)

    }

    private fun setupViewPods() {
        mBestAndPopularMovieListViewPod = vpBestAndPopularMovieList as MovieListViewPod
        mGeneresMovieListViewPod = vpGenresMovieList as MovieListViewPod
        mActorListViewPod = vpActorList as PersonListViewPod


        mBestAndPopularMovieListViewPod.setUpMovieListViewPod(mainPresenter)
        mGeneresMovieListViewPod.setUpMovieListViewPod(mainPresenter)

    }

    private fun setupGeneresTabLayout(genresList: List<GenreVO>) {
        genresList.forEach { genre ->
            tabLayoutGeneres.newTab().apply {
                text = genre.name
                tabLayoutGeneres.addTab(this)
            }
        }
    }

    private fun setupListener() {
        tabLayoutGeneres.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Snackbar.make(window.decorView, tab?.text ?: "", Snackbar.LENGTH_SHORT).show()
                tab?.let {
                    mainPresenter.getMoviesByGenres(position = it.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    private fun setupShowcase() {
        mShowcaseAdapter = ShowcaseAdapter(mainPresenter)
        rvShowcase.adapter = mShowcaseAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }


    override fun showNowPlayingMovies(movieList: List<MovieVO>) {
        mBannerAdapter.setNewData(movieList)
    }

    override fun showPopularMovies(movieList: List<MovieVO>) {
        mBestAndPopularMovieListViewPod.setData(movieList)
    }

    override fun showTopRatedMovies(movieList: List<MovieVO>) {
        mShowcaseAdapter.setNewData(movieList)
    }

    override fun showGenreList(genreList: List<GenreVO>) {
        setupGeneresTabLayout(genreList)
    }

    override fun showMoviesByGenre(movieList: List<MovieVO>) {
        mGeneresMovieListViewPod.setData(movieList)
    }

    override fun showActors(actorList: List<ActorVO>) {
        mActorListViewPod.setData(actorList)
    }

    override fun showError(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showMessage(message: String){
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun navigateToMovieDetailScreen(movieId: Int) {
        navigateToMovieDetailActivity(movieId = movieId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.actionDiscover){
            navigateToMovieSearchActivity()
        }
        return super.onOptionsItemSelected(item)
    }
}