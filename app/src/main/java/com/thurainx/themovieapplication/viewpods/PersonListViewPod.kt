package com.thurainx.themovieapplication.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.thurainx.themovieapplication.adapters.PersonAdapter
import kotlinx.android.synthetic.main.viewpod_person_list.view.*

class PersonListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    lateinit var mPersonAdapter: PersonAdapter
    override fun onFinishInflate() {
        mPersonAdapter = PersonAdapter()
        rvPersonList.adapter = mPersonAdapter
        super.onFinishInflate()
    }

}