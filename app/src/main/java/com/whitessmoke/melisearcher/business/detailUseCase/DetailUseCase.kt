package com.whitessmoke.melisearcher.business.detailUseCase

import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.data.detail.network.DetailService
import com.whitessmoke.melisearcher.ext.isNull
import java.lang.ArithmeticException
import java.lang.Exception
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private var detailService: DetailService
) {

    /**
     * Consulta al servicio encargado de obtener los datos de un producto dado
     * @param id  codigo MCO del producto en cuestion
     */
    suspend fun getDetails(id: String): ModelProduct? {
        val model = detailService.getDetail(id)
        val description = detailService.getDescription(id)
        description?.let {
            model?.description = description
        }

        return model

    }


}