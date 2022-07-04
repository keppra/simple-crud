package com.baseaplication.api.services

import com.baseaplication.api.models.books.BookModel
import com.baseaplication.api.models.books.BooksWrapperModel
import io.reactivex.rxjava3.core.Single

interface Services {
    fun searchBooks(query: String): Single<BooksWrapperModel<BookModel>>
    fun searchBooks(query: String,
                    startIndex: Int,
                    maxResults: Int
    ): Single<BooksWrapperModel<BookModel>>
}