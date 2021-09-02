package com.whitessmoke.melisearcher.data.detail.model

import com.google.gson.annotations.SerializedName

data class DetailModelUserLocation(
    @SerializedName("city") val city: DetailModelCity,
    @SerializedName("state") val state: DetailModelState,
    @SerializedName("country") val country: DetailModelCountry,
)