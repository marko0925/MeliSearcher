package com.whitessmoke.melisearcher.data.detail.model

import com.google.gson.annotations.SerializedName

data class DetailModelAttributeProduct(
    @SerializedName("name") val name:String,
    @SerializedName("value_name") val value:String,
)
