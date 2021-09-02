package com.whitessmoke.melisearcher.adapters.detail

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.data.detail.model.DetailModelAttributeCombination
import com.whitessmoke.melisearcher.data.detail.model.DetailModelVariation
import com.whitessmoke.melisearcher.databinding.ItemDetailAttributeCombinationBinding
import com.whitessmoke.melisearcher.databinding.ItemDetailVariationBinding

class DetailVariationsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ItemDetailVariationBinding.bind(itemView)
    lateinit var adapter: DetailAttributeCombinationAdapter

    fun bind(model: DetailModelVariation) {
        adapter = DetailAttributeCombinationAdapter(model.attributesCombinations)
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(itemView.context)
    }
}