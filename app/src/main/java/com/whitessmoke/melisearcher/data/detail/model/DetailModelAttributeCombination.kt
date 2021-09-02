package com.whitessmoke.melisearcher.data.detail.model

import com.google.gson.annotations.SerializedName

data class DetailModelAttributeCombination(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("value_name") val value: String,
)