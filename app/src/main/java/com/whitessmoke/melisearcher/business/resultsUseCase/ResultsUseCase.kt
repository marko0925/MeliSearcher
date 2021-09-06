package com.whitessmoke.melisearcher.business.resultsUseCase

import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import com.whitessmoke.melisearcher.data.result.network.ResultService
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class ResultsUseCase @Inject constructor(

    private var resultService: ResultService
) {

    private var offset: Int = 0


    /**
     * Consulta al servicio encargado de obtener los productos que concuerden con el criterio
     * de busqueda
     * @param str query de la busqueda
     * @return ResultModelResponse modelo de datos de la respuesta de la api de MELI
     */
    suspend fun nextPage(str: String) = resultService.getResultsService(str, offset)

    /**
     * Incrementa el offset para la proxima paginación
     * @param offset offset de la busqueda
     */
    fun increaseOffset(offset: Int) {
        this.offset = offset
    }

}