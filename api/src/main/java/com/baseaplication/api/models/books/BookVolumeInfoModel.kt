package com.baseaplication.api.models.books

import com.google.gson.annotations.SerializedName

data class BookVolumeInfoModel(
    @SerializedName("title") val title: String? = null,
    @SerializedName("authors") val authors: List<String>? = null,
    @SerializedName("publisher") val publisher: String? = null,
    @SerializedName("publishedDate") val publishedDate: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("industryIdentifiers") val industryIdentifiers: List<BookIndustryIdentifier>? = null,
    @SerializedName("pageCount") val pageCount: Int? = null,
    @SerializedName("imageLinks") val imageLinks: BookImageLinks? = null,
    @SerializedName("language") val language: String? = null
)