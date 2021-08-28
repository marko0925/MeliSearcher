package com.whitessmoke.melisearcher.data.result.model

import com.google.gson.annotations.SerializedName

data class ResultMeliImg(
    @SerializedName("secure_url") val img: String,
    @SerializedName("size") val size: String,

    )
