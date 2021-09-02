package com.whitessmoke.melisearcher.adapters.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.R
import com.whitessmoke.melisearcher.data.detail.model.DetailModelAttributeCombination
import com.whitessmoke.melisearcher.data.detail.model.DetailModelVariation

class DetailVariationsAdapter(var variations: List<DetailModelVariation>) :
    RecyclerView.Adapter<DetailVariationsVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailVariationsVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail_variation, parent, false)
        return DetailVariationsVH(v)
    }

    override fun onBindViewHolder(holder: DetailVariationsVH, position: Int) {
        val model = variations[position]
        holder.bind(model)
    }

    override fun getItemCount() = variations.size

    fun setItems(variations: List<DetailModelVariation>) {
        this.variations = variations
    }
}