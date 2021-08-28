package com.whitessmoke.melisearcher.business.resultsUseCase

import com.whitessmoke.melisearcher.data.result.model.ResultModelProduct
import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import com.whitessmoke.melisearcher.data.result.network.ResultService
import javax.inject.Inject

class ResultsUseCase @Inject constructor(
    var resultService: ResultService
) {
    var offset: Int = 0
    suspend fun nextPage(str: String): ResultModelResponse? {

        val response = resultService.getResultsService(str, offset)
        this.offset += response?.results?.size ?: 0
        return response
    }

}