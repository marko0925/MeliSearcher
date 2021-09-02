package com.whitessmoke.melisearcher.data.detail.model

import com.google.gson.annotations.SerializedName

data class DetailModelPictures(
    @SerializedName("url") var url: String,
    @SerializedName("size") var size: String
)