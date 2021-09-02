package com.whitessmoke.melisearcher.data.detail.network

import com.whitessmoke.melisearcher.data.common.ModelProduct
import com.whitessmoke.melisearcher.data.detail.model.DetailModelDescription
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailApi {
    @GET("/items/{id}")
    suspend fun getDetails(@Path("id") id: String): Response<ModelProduct>

    @GET("/items/{id}/description")
    suspend fun getDescription(@Path("id") id: String): Response<DetailModelDescription>
}