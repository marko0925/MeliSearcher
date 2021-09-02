package com.whitessmoke.melisearcher.data.detail.network

import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.data.detail.model.DetailModelDescription
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DetailService @Inject constructor(
    private var api: DetailApi
) {

    /**
     * Consulta los datos de la api de MELI a traves de retrofit2 obteniendo las caracteristicas
     * del producto
     * @param id codigo MCO del producto
     * @return ModelProduct modelo de datos del producto
     */
    suspend fun getDetail(id: String): ModelProduct? {
        return withContext(Dispatchers.IO) {
            val resp = api.getDetails(id)
            resp.body()
        }
    }

    /**
     * Consulta la descripción de un producto de MELI
     * @param id codigo MCO del producto
     * @return DetailModelDescription modelo de datos de descripcion
     */
    suspend fun getDescription(id: String): DetailModelDescription? {
        return withContext(Dispatchers.IO) {
            val resp = api.getDescription(id)
            resp.body()
        }
    }
}