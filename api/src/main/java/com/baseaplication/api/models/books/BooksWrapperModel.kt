package com.baseaplication.api.models.books

import com.google.gson.annotations.SerializedName

data class BooksWrapperModel<T>(
    @SerializedName("kind") val kind: String,
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("items") val items: List<T>
)