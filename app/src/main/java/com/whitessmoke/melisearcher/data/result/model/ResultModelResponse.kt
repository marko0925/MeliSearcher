package com.whitessmoke.melisearcher.data.result.model

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName

data class ResultModelResponse(
    @SerializedName("query") var query: String,
    @SerializedName("paging") val paging: ResultModelPaging,
    @SerializedName("results") var results: List<ResultModelProduct>

)