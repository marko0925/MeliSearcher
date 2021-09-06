package com.whitessmoke.melisearcher.data.result.model

import com.google.gson.annotations.SerializedName
import com.whitessmoke.melisearcher.data.common.ModelProduct

data class ResultModelResponse(
    @SerializedName("query") var query: String,
    @SerializedName("paging") var paging: ResultModelPaging,
    @SerializedName("results") var results: List<ModelProduct>

)