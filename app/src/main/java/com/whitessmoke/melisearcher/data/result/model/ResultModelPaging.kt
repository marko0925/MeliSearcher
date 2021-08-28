package com.whitessmoke.melisearcher.data.result.model

import com.google.gson.annotations.SerializedName

data class ResultModelPaging(
    @SerializedName("total") val total: Int,
    @SerializedName("offset") val offset: Int
)
