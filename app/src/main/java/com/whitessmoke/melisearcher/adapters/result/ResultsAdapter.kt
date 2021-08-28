package com.whitessmoke.melisearcher.adapters.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.R
import com.whitessmoke.melisearcher.data.result.model.ResultModelProduct
import javax.inject.Inject

class ResultsAdapter():
    RecyclerView.Adapter<ResultsVH>() {
    var results = ArrayList<ResultModelProduct>()

    fun addItems(list: List<ResultModelProduct>) {
        val indexInserted = results.size
        results.addAll(list)
        notifyItemRangeInserted(indexInserted, list.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ResultsVH(layoutInflater.inflate(R.layout.item_result, parent, false))
    }

    override fun onBindViewHolder(holder: ResultsVH, position: Int) {
        val item = results[position]
        holder.bind(item)
    }

    override fun getItemCount() = results.size

}