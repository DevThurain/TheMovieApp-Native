package com.thurainx.themovieapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.adapters.BannerAdapter
import com.thurainx.themovieapplication.adapters.ShowcaseAdapter
import com.thurainx.themovieapplication.dummy.dummyGeneresList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mBannerAdapter : BannerAdapter
    lateinit var mShowcaseAdapter: ShowcaseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
        setupBannerViewPager()
        setupGeneresTabLayout()
        setupListener()
        setupShowcase()

    }

    private fun setupActionBar() {
        setSupportActionBar(toolBar)
        toolBar.setNavigationIcon(R.drawable.ic_menu)
    }

    private fun setupBannerViewPager(){
        mBannerAdapter = BannerAdapter()
        viewPagerBanner.adapter = mBannerAdapter
        dotsIndicatorBanner.attachTo(viewPagerBanner)

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
        mShowcaseAdapter = ShowcaseAdapter()
        rvShowcase.adapter = mShowcaseAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover,menu)
        return true
    }
}