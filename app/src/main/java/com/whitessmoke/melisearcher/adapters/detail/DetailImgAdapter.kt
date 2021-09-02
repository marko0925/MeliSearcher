package com.whitessmoke.melisearcher.adapters.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.R
import com.whitessmoke.melisearcher.data.detail.model.DetailModelPictures

class DetailImgAdapter(var pictures: List<DetailModelPictures>) :
    RecyclerView.Adapter<DetailImgVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImgVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DetailImgVH(layoutInflater.inflate(R.layout.item_detail_view_pager, parent, false))
    }

    override fun onBindViewHolder(holder: DetailImgVH, position: Int) {
        val item = pictures[position]
        holder.bind(item)

    }

    override fun getItemCount() = pictures.size

    fun setItems(pictures: List<DetailModelPictures>) {
        this.pictures = pictures
        notifyDataSetChanged()
    }

}