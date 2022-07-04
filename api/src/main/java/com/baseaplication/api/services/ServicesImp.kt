package com.baseaplication.api.services

import com.baseaplication.api.BuildConfig
import com.baseaplication.api.models.books.BookModel
import com.baseaplication.api.models.books.BooksWrapperModel
import io.reactivex.rxjava3.core.Single

const val START_INDEX = 0
const val MAX_RESULTS = 40

class ServicesImp(
    private val googleBooksApiServices: GoogleBooksAPIServices
): Services {
    override fun searchBooks(query: String): Single<BooksWrapperModel<BookModel>> =
        googleBooksApiServices.searchBooks(
            query = query,
            startIndex = START_INDEX,
            maxResults = MAX_RESULTS,
            key = BuildConfig.KEY_API_GOOGLE_BOOKS
        )

    override fun searchBooks(
        query: String,
        startIndex: Int,
        maxResults: Int
    ): Single<BooksWrapperModel<BookModel>> =
        googleBooksApiServices.searchBooks(
            query = query,
            startIndex = startIndex,
            maxResults = maxResults,
            key = BuildConfig.KEY_API_GOOGLE_BOOKS
        )

}