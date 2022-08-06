package com.thurainx.themovieapplication.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thurainx.themovieapplication.data.vos.ActorVO
import com.thurainx.themovieapplication.utils.IMAGE_BASED_URL
import kotlinx.android.synthetic.main.view_holder_person.view.*

class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(actor: ActorVO){
        itemView.tvPersonName.text = actor.name
        Glide.with(itemView.context)
            .load(IMAGE_BASED_URL.plus(actor.profilePath))
            .into(itemView.ivPersonCover)
    }
}