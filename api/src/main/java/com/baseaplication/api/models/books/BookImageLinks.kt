package com.baseaplication.api.models.books

import com.google.gson.annotations.SerializedName

data class BookImageLinks(
    @SerializedName("smallThumbnail") val smallThumbnail: String,
    @SerializedName("thumbnail") val thumbnail: String
)
