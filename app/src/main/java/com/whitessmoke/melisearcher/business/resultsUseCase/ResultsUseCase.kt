package com.whitessmoke.melisearcher.business.resultsUseCase

import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import com.whitessmoke.melisearcher.data.result.network.ResultService
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class ResultsUseCase @Inject constructor(

    private var resultService: ResultService
) {
    var offset: Int = 0


    /**
     * Consulta al servicio encargado de obtener los productos que concuerden con el criterio
     * de busqueda
     * @param str query de la busqueda
     * @throws RuntimeException excepcion generica para cuando falla el servicio y muestra un mensaje
     * generico al usuario
     * @return ResultModelResponse modelo de datos de la respuesta de la api de MELI
     */
    suspend fun nextPage(str: String): ResultModelResponse? {
        var response: ResultModelResponse? = null
        try {

            response = resultService.getResultsService(str, offset)
            return response
        } catch (e: Exception) {
            throw RuntimeException("¡Ops! algo ha ido mal, intentalo de nuevo mas tarde")
        }
    }

    /**
     * Incrementa el offset para la proxima paginación
     * @param offset offset de la busqueda
     */
    fun increaseOffset(offset: Int) {
        this.offset = offset
    }

}