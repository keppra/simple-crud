package com.baseaplication.api.models.books

import com.google.gson.annotations.SerializedName

data class BookModel(
    @SerializedName("kind") val kind: String,
    @SerializedName("id") val id: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("selfLink") val selfLink: String,
    @SerializedName("volumeInfo") val volumeInfo: BookVolumeInfoModel
)