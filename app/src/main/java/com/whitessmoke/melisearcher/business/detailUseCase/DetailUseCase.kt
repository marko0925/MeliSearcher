package com.whitessmoke.melisearcher.business.detailUseCase

import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.data.detail.network.DetailService
import java.lang.Exception
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private var detailService: DetailService
) {

    /**
     * Consulta al servicio encargado de obtener los datos de un producto dado
     * @param id  codigo MCO del producto en cuestion
     * @throws RuntimeException excepcion generica para cuando falla el servicio y muestra un mensaje
     * generico al usuario
     */
    suspend fun getDetails(id: String): ModelProduct? {
        try {
            val model = detailService.getDetail(id)
            val description = detailService.getDescription(id)
            if (description != null) {
                model?.description = description
            }
            return model
        } catch (e: Exception) {
            throw RuntimeException("¡Ops! algo ha ido mal, intentalo de nuevo mas tarde")
        }

    }


}