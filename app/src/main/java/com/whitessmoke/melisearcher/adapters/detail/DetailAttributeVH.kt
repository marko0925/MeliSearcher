package com.whitessmoke.melisearcher.adapters.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.data.detail.model.DetailModelAttributeProduct
import com.whitessmoke.melisearcher.databinding.ItemDetailAttributeCombinationBinding
import com.whitessmoke.melisearcher.databinding.ItemDetailAttributeProductBinding

class DetailAttributeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemDetailAttributeProductBinding.bind(itemView)
    fun bind(model: DetailModelAttributeProduct) {
        binding.text.text = model.name
        binding.value.text = model.value

    }
}