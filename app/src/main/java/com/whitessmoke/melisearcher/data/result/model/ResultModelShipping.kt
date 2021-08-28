package com.whitessmoke.melisearcher.data.result.model

import com.google.gson.annotations.SerializedName

data class ResultModelShipping(
    @SerializedName("free_shipping") val free: Boolean
)
