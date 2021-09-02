package com.whitessmoke.melisearcher.data.result.network

import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ResultService @Inject constructor(
    private val api: ResultApi,

    ) {

    suspend fun getResultsService(str: String, offset: Int?): ResultModelResponse? {
        return withContext(Dispatchers.IO) {
            val resp = api.getResults(str, offset ?: 0)
            resp.body()
        }
    }

}