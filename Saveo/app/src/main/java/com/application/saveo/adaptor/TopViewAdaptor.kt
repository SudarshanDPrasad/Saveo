package com.application.saveo.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.saveo.R
import com.application.saveo.clicklistener.onClick
import com.application.saveo.databinding.MainscrolllayoutBinding
import com.application.saveo.databinding.TopscrollinglayoutBinding
import com.application.saveo.response.ResultDTO
import com.bumptech.glide.Glide


class TopViewAdaptor(
    val onClick: onClick
) : PagingDataAdapter<ResultDTO, TopViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResultDTO>() {
            override fun areItemsTheSame(
                oldItem: ResultDTO,
                newItem: ResultDTO,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResultDTO,
                newItem: ResultDTO,
            ): Boolean {
                return oldItem.equals(newItem)
            }

        }
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        val result = getItem(position)
        result?.let {
            holder.setData(it)

            holder.topscrollinglayoutBinding.ivTopimage.setOnClickListener {
                onClick.clickListener(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {

        val topscrollinglayoutBinding : TopscrollinglayoutBinding=
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.topscrollinglayout, parent, false)
        return TopViewHolder(topscrollinglayoutBinding)
    }
}

class TopViewHolder(
    val topscrollinglayoutBinding: TopscrollinglayoutBinding,
) : RecyclerView.ViewHolder(topscrollinglayoutBinding.root) {

    fun setData(resultDTO: ResultDTO){
        Glide.with(topscrollinglayoutBinding.ivTopimage).load("https://image.tmdb.org/t/p/w500/"+resultDTO.posterPath).into(topscrollinglayoutBinding.ivTopimage)
    }

}
