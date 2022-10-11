package com.thurainx.themovieapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.adapters.BannerAdapter
import com.thurainx.themovieapplication.adapters.ShowcaseAdapter
import com.thurainx.themovieapplication.data.models.MovieModel
import com.thurainx.themovieapplication.data.models.MovieModelImpl
import com.thurainx.themovieapplication.data.vos.GenreVO
import com.thurainx.themovieapplication.delegates.BannerDelegate
import com.thurainx.themovieapplication.delegates.MovieDelegate
import com.thurainx.themovieapplication.delegates.ShowcaseDelegate
import com.thurainx.themovieapplication.dummy.dummyGeneresList
import com.thurainx.themovieapplication.mvi.intent.MainIntent
import com.thurainx.themovieapplication.mvi.mviBased.MVIView
import com.thurainx.themovieapplication.mvi.state.MainState
import com.thurainx.themovieapplication.mvi.viewmodel.MainViewModel
import com.thurainx.themovieapplication.network.dataagents.MovieDataAgentImpl
import com.thurainx.themovieapplication.network.dataagents.OkhttpDataAgentImpl
import com.thurainx.themovieapplication.network.dataagents.RetrofitDataAgentImpl
import com.thurainx.themovieapplication.viewpods.MovieListViewPod
import com.thurainx.themovieapplication.viewpods.PersonListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerDelegate, MovieDelegate, ShowcaseDelegate, MVIView<MainState> {
    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter
    lateinit var mBestAndPopularMovieListViewPod: MovieListViewPod
    lateinit var mGeneresMovieListViewPod: MovieListViewPod
    lateinit var mActorListViewPod: PersonListViewPod

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()

        setupActionBar()
        setupBannerViewPager()
        setupViewPods()
        setupListener()
        setupShowcase()

        setInitialIntent()
        observeState()

    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun setInitialIntent(){
        mainViewModel.processIntent(intent = MainIntent.getHomePageData, owner = this)
    }

    private fun observeState(){
        mainViewModel.state.observe(this, this::render)
    }


    private fun setupActionBar() {
        setSupportActionBar(toolBar)
        toolBar.setNavigationIcon(R.drawable.ic_menu)
    }

    private fun setupBannerViewPager() {
        mBannerAdapter = BannerAdapter(this)
        viewPagerBanner.adapter = mBannerAdapter
        dotsIndicatorBanner.attachTo(viewPagerBanner)

    }

    private fun setupViewPods() {
        mBestAndPopularMovieListViewPod = vpBestAndPopularMovieList as MovieListViewPod
        mGeneresMovieListViewPod = vpGenresMovieList as MovieListViewPod
        mActorListViewPod = vpActorList as PersonListViewPod


        mBestAndPopularMovieListViewPod.setUpMovieListViewPod(this)
        mGeneresMovieListViewPod.setUpMovieListViewPod(this)

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
                    mainViewModel.processIntent(intent = MainIntent.getMovieListByGenre(position = it.position), owner = this@MainActivity)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    private fun setupShowcase() {
        mShowcaseAdapter = ShowcaseAdapter(this)
        rvShowcase.adapter = mShowcaseAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onTapBanner(movieId: Int?) {
//        Snackbar.make(window.decorView,"Banner Click",Snackbar.LENGTH_SHORT).show()
        val intent = MovieDetailActivity.newIntent(this, movieId)
        startActivity(intent)
    }


    override fun onTapMovie(movieId: Int?) {
//        Snackbar.make(window.decorView,"Best Popular Movie Click or Generes Movie Click",Snackbar.LENGTH_SHORT).show()
        val intent = MovieDetailActivity.newIntent(this, movieId)
        startActivity(intent)
    }

    override fun onTapShowcase(movieId: Int?) {
//        Snackbar.make(window.decorView,"Showcase Click",Snackbar.LENGTH_SHORT).show()

        val intent = MovieDetailActivity.newIntent(this, movieId)
        startActivity(intent)
    }

    private fun showError(error: String) {
        Snackbar.make(window.decorView, error, Snackbar.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.actionDiscover){
            val intent = MovieSearchActivity.newIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun render(state: MainState) {
        if(state.errorMessage.isNotEmpty()){
            showError(state.errorMessage)
        }
        Log.d("render", "rendering...")
        mBannerAdapter.setNewData(state.nowPlayingMovieList)
        mBestAndPopularMovieListViewPod.setData(state.popularMovieList)
        mShowcaseAdapter.setNewData(state.topRatedMovieList)
        setupGeneresTabLayout(state.genreList)
        mActorListViewPod.setData(state.actorList)
        mGeneresMovieListViewPod.setData(state.movieListByGenre)


    }
}