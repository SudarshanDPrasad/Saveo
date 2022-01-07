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
import com.application.saveo.response.ResultDTO
import com.application.saveo.ui.MainActivity
import com.bumptech.glide.Glide


class mainViewAdaptor(
var onClick: onClick
) : PagingDataAdapter<ResultDTO, MainViewHolder>(diffUtil) {

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

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val result = getItem(position)
        result?.let {
            holder.setData(it)
            holder.mainscrolllayoutBinding.ivImageLayout.setOnClickListener {
                onClick.clickListener(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val mainscrolllayoutBinding: MainscrolllayoutBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.mainscrolllayout, parent, false)
        return MainViewHolder(mainscrolllayoutBinding)
    }
}

class MainViewHolder(
    val mainscrolllayoutBinding: MainscrolllayoutBinding,
) : RecyclerView.ViewHolder(mainscrolllayoutBinding.root) {

    fun setData(resultDTO: ResultDTO) {
        Glide.with(mainscrolllayoutBinding.ivImageLayout)
            .load("https://image.tmdb.org/t/p/w500/" + resultDTO.posterPath)
            .into(mainscrolllayoutBinding.ivImageLayout)
    }

}
