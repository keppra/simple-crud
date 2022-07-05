package com.baseaplication.api.models.books

import com.google.gson.annotations.SerializedName

data class BookIndustryIdentifier(
    @SerializedName("type") val type: String,
    @SerializedName("identifier") val identifier: String
)