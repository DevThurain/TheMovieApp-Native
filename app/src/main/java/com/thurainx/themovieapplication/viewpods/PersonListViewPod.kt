package com.thurainx.themovieapplication.viewpods

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.adapters.PersonAdapter
import kotlinx.android.synthetic.main.viewpod_person_list.view.*

class PersonListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var mPersonAdapter: PersonAdapter
    override fun onFinishInflate() {
        setupPersonRecyclerView()
        super.onFinishInflate()
    }

    fun setupPersonViewpod(backgroundColorReference: Int,titleText: String,moreTitleText: String){
        setBackgroundColor(backgroundColorReference)
        tvPersonTitle.text = titleText
        tvMore.text = moreTitleText
        tvMore.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun setupPersonRecyclerView(){
        mPersonAdapter = PersonAdapter()
        rvPersonList.adapter = mPersonAdapter
    }

}