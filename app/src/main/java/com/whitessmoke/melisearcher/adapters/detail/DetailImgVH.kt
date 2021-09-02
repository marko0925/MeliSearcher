package com.whitessmoke.melisearcher.adapters.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.whitessmoke.melisearcher.data.detail.model.DetailModelPictures
import com.whitessmoke.melisearcher.databinding.ItemDetailViewPagerBinding

class DetailImgVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemDetailViewPagerBinding.bind(itemView)
    fun bind(modelPictures: DetailModelPictures) {
        Glide.with(itemView.context).load(modelPictures.url).into(binding.iv)
    }
}