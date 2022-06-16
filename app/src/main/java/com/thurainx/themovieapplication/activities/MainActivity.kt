package com.thurainx.themovieapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.adapters.BannerAdapter
import com.thurainx.themovieapplication.adapters.ShowcaseAdapter
import com.thurainx.themovieapplication.delegates.BannerDelegate
import com.thurainx.themovieapplication.delegates.MovieDelegate
import com.thurainx.themovieapplication.delegates.ShowcaseDelegate
import com.thurainx.themovieapplication.dummy.dummyGeneresList
import com.thurainx.themovieapplication.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerDelegate, MovieDelegate, ShowcaseDelegate {
    lateinit var mBannerAdapter : BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter
    lateinit var mBestAndPopularMovieListViewPod: MovieListViewPod
    lateinit var mGeneresMovieListViewPod: MovieListViewPod
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
        setupBannerViewPager()
        setupViewPods()
        setupGeneresTabLayout()
        setupListener()
        setupShowcase()

    }

    private fun setupActionBar() {
        setSupportActionBar(toolBar)
        toolBar.setNavigationIcon(R.drawable.ic_menu)
    }

    private fun setupBannerViewPager(){
        mBannerAdapter = BannerAdapter(this)
        viewPagerBanner.adapter = mBannerAdapter
        dotsIndicatorBanner.attachTo(viewPagerBanner)

    }

    private fun setupViewPods(){
        mBestAndPopularMovieListViewPod = vpBestAndPopularMovieList as MovieListViewPod
        mGeneresMovieListViewPod = vpGenresMovieList as MovieListViewPod

        mBestAndPopularMovieListViewPod.setUpMovieListViewPod(this)
        mGeneresMovieListViewPod.setUpMovieListViewPod(this)
    }

    private fun setupGeneresTabLayout(){
        dummyGeneresList.forEach { genre ->
            tabLayoutGeneres.newTab().apply {
                text = genre
                tabLayoutGeneres.addTab(this)
            }
        }
    }

    private fun setupListener(){
        tabLayoutGeneres.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
               Snackbar.make(window.decorView,tab?.text ?: "",Snackbar.LENGTH_SHORT).show()
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
        menuInflater.inflate(R.menu.menu_discover,menu)
        return true
    }

    override fun onTapBanner() {
//        Snackbar.make(window.decorView,"Banner Click",Snackbar.LENGTH_SHORT).show()
        val intent = MovieDetailActivity.newIntent(this)
        startActivity(intent)
    }


    override fun onTapMovie() {
//        Snackbar.make(window.decorView,"Best Popular Movie Click or Generes Movie Click",Snackbar.LENGTH_SHORT).show()
        val intent = MovieDetailActivity.newIntent(this)
        startActivity(intent)
    }

    override fun onTapShowcase() {
//        Snackbar.make(window.decorView,"Showcase Click",Snackbar.LENGTH_SHORT).show()

        val intent = MovieDetailActivity.newIntent(this)
        startActivity(intent)
    }
}