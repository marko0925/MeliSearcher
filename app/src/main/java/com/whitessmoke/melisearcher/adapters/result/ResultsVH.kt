package com.whitessmoke.melisearcher.adapters.result

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.whitessmoke.melisearcher.data.result.model.ResultModelProduct
import com.whitessmoke.melisearcher.databinding.ItemResultBinding
import com.whitessmoke.melisearcher.ext.toPrice

class ResultsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ItemResultBinding.bind(itemView)
    fun bind(model: ResultModelProduct) {

        binding.freeShipping.isVisible = model.shipping.free
        if (model.shipping.free) {
            binding.freeShipping.text = "Envio gratis"
        }
        binding.stock.text = if (model.stock == 1) "¡Ultimo disponible!" else "${model.stock} unidades disponibles"
        binding.title.text = model.title
        binding.price.text = model.price.toPrice()
        Glide.with(itemView.context).load(model.img).into(binding.iv)

    }
}