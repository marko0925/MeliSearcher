package com.whitessmoke.melisearcher.adapters.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.data.detail.model.DetailModelAttributeCombination
import com.whitessmoke.melisearcher.databinding.ItemDetailAttributeCombinationBinding

class DetailAttributeCombinationVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemDetailAttributeCombinationBinding.bind(itemView)

    fun bind(model: DetailModelAttributeCombination) {
        binding.text.text = model.name
        binding.value.text = model.value
    }
}