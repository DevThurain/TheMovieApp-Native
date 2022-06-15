package com.thurainx.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.viewholders.ShowcaseViewHolder

class ShowcaseAdapter : RecyclerView.Adapter<ShowcaseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_showcase,parent,false)

        return ShowcaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowcaseViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return 4
    }
}