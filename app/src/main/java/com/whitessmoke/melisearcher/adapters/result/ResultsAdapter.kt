package com.whitessmoke.melisearcher.adapters.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whitessmoke.melisearcher.R
import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.ext.isNull

class ResultsAdapter(var listenerClicked: IItemResultClicked) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * Constantes de tipos de celdas
     */
    val VIEW_TYPE_LOADING = 22
    val VIEW_TYPE_ROW = 23

    var results = ArrayList<ModelProduct?>()

    fun addItems(list: List<ModelProduct>) {
        val indexInserted = results.size
        /**
         * Eliminamos el ResultItemLoadingVH anterior en caso que exista con el fin de eliminar el
         * row de progreso
         */
        if (results.size > 0 && results.get(results.size - 1).isNull()) {
            results.removeAt(results.size - 1)
        }
        results.addAll(list)
        /**
         * Añadimos en la ultima posicion objecto nulo con el fin de mostrar el loading
         */
        results.add(null)
        notifyItemRangeInserted(indexInserted, list.size + 1)
    }

    override fun getItemViewType(position: Int) =
        if (results.get(position).isNull()) VIEW_TYPE_LOADING else VIEW_TYPE_ROW

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_LOADING) {
            return ResultItemLoadingVH(
                layoutInflater.inflate(
                    R.layout.item_result_loading,
                    parent,
                    false
                )
            )
        }
        return ResultItemRowVH(
            layoutInflater.inflate(R.layout.item_result_row, parent, false),
            listenerClicked
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ResultItemRowVH) {
            val item = results[position]
            item?.let { holder.bind(it) }

        }

    }

    override fun getItemCount() = results.size


}