package com.whitessmoke.melisearcher.adapters.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.R
import com.whitessmoke.melisearcher.data.detail.model.DetailModelAttributeCombination

class DetailAttributeCombinationAdapter(var attributes: List<DetailModelAttributeCombination>) :
    RecyclerView.Adapter<DetailAttributeCombinationVH>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailAttributeCombinationVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_attribute_combination, parent, false)
        return DetailAttributeCombinationVH(v)
    }

    override fun onBindViewHolder(holder: DetailAttributeCombinationVH, position: Int) {
        val model = attributes[position]
        holder.bind(model)
    }

    override fun getItemCount() = attributes.size
}