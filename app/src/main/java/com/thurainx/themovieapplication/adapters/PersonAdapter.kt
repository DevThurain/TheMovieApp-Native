package com.thurainx.themovieapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thurainx.themovieapplication.R
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.viewholders.PersonViewHolder

class PersonAdapter : RecyclerView.Adapter<PersonViewHolder>(){
    var mActorList: List<ActorVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_person,parent,false)

        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(mActorList[position])
    }

    override fun getItemCount(): Int {
       return mActorList.size
    }

    fun setNewData(actorList: List<ActorVO>){
        mActorList = actorList
        notifyDataSetChanged()
    }
}