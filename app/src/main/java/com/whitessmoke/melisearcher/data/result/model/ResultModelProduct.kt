package com.whitessmoke.melisearcher.data.result.model

import com.google.gson.annotations.SerializedName

data class ResultModelProduct(
    @SerializedName("id") var id: String,
    @SerializedName("title") var title: String,
    @SerializedName("price") var price: Int,
    @SerializedName("available_quantity") var stock: Int,
    @SerializedName("sold_quantity") var sold: Int,
    @SerializedName("shipping") val shipping: ResultModelShipping,
    @SerializedName("thumbnail") val img: String,
)