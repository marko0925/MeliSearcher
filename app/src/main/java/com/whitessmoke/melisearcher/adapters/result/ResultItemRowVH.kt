package com.whitessmoke.melisearcher.adapters.result

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.databinding.ItemResultRowBinding
import com.whitessmoke.melisearcher.ext.toPrice

class ResultItemRowVH(itemView: View, var iItemResultClicked: IItemResultClicked) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val binding = ItemResultRowBinding.bind(itemView)
    lateinit var model: ModelProduct

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(model: ModelProduct) {
        this.model = model
        binding.freeShipping.isVisible = model.shipping.free
        if (model.shipping.free) {
            binding.freeShipping.text = "Envio gratis"
        }
        binding.stock.text =
            if (model.stock == 1) "¡Ultimo disponible!" else "${model.stock} unidades disponibles"
        binding.title.text = model.title
        binding.price.text = model.price.toPrice()
        Glide.with(itemView.context).load(model.img).into(binding.iv)

    }

    override fun onClick(v: View?) {
        iItemResultClicked.itemClicked(model)
    }
}