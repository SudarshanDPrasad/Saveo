package com.application.saveo.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.saveo.R
import com.application.saveo.clicklistener.onClick
import com.application.saveo.databinding.TopscrollinglayoutBinding
import com.application.saveo.response.ResultDTO
import com.bumptech.glide.Glide

class NewAdaptor(
    val list : List<ResultDTO>,
    val onClick: onClick
): RecyclerView.Adapter<NewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        val topscrollinglayoutBinding: TopscrollinglayoutBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.topscrollinglayout,parent,false)

        return NewHolder(topscrollinglayoutBinding)
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        val NewList = list[position]
        holder.setData(NewList)
        holder.topscrollinglayoutBinding.ivTopimage.setOnClickListener {
         onClick.clickListener(NewList)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class NewHolder(
    val topscrollinglayoutBinding: TopscrollinglayoutBinding,
) : RecyclerView.ViewHolder(topscrollinglayoutBinding.root) {

    fun setData(resultDTO: ResultDTO){
        Glide.with(topscrollinglayoutBinding.ivTopimage).load("https://image.tmdb.org/t/p/w500/"+resultDTO.posterPath).into(topscrollinglayoutBinding.ivTopimage)
    }
}
