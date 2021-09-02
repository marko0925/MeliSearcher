package com.whitessmoke.melisearcher.data.common

import com.google.gson.annotations.SerializedName
import com.whitessmoke.melisearcher.data.detail.model.*
import com.whitessmoke.melisearcher.data.result.model.ResultModelShipping

data class ModelProduct(
    @SerializedName("id") var id: String,
    @SerializedName("title") var title: String,
    @SerializedName("price") var price: Int,
    @SerializedName("available_quantity") var stock: Int,
    @SerializedName("sold_quantity") var sold: Int,
    @SerializedName("shipping") val shipping: ResultModelShipping,
    @SerializedName("thumbnail") val img: String,
    @SerializedName("pictures") val pictures: List<DetailModelPictures>,
    @SerializedName("condition") val condition: String,
    @SerializedName("warranty") val warranty: String,
    @SerializedName("seller_address") val sellerAddess: DetailModelUserLocation,
    var description: DetailModelDescription,
    @SerializedName("variations") val variations: List<DetailModelVariation>,
    @SerializedName("attributes") val attributes: List<DetailModelAttributeProduct>,
)