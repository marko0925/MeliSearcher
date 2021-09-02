package com.whitessmoke.melisearcher.adapters.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.R
import com.whitessmoke.melisearcher.data.detail.model.DetailModelAttributeProduct

class DetailAttributeAdapter(var attributes: List<DetailModelAttributeProduct>) :
    RecyclerView.Adapter<DetailAttributeVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAttributeVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_attribute_product, parent, false)
        return DetailAttributeVH(v)
    }

    override fun onBindViewHolder(holder: DetailAttributeVH, position: Int) {
        val model = attributes[position]
        holder.bind(model)
    }

    override fun getItemCount() = attributes.size
    fun setItems(attributes: List<DetailModelAttributeProduct>) {
        this.attributes = attributes
    }
}