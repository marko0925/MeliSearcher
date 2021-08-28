package com.whitessmoke.melisearcher.data.result.network

import com.whitessmoke.melisearcher.data.result.model.ResultModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ResultApi {
    @GET("/sites/MCO/search")
    suspend fun getResults(
        @Query("q") str: String,
        @Query("offset") offset: Int
    ): Response<ResultModelResponse>
}