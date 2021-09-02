package com.whitessmoke.melisearcher.data.detail.model

import com.google.gson.annotations.SerializedName

data class DetailModelVariation(
    @SerializedName("price") val price: Int,
    @SerializedName("attribute_combinations") val attributesCombinations: List<DetailModelAttributeCombination>,
)