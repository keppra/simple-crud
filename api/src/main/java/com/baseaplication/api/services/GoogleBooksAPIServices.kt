package com.baseaplication.api.services

import com.baseaplication.api.models.books.BookModel
import com.baseaplication.api.models.books.BooksWrapperModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksAPIServices {

    @GET("volumes")
    fun searchBooks(@Query("q") query: String,
                    @Query("key") key: String
    ): Single<BooksWrapperModel<BookModel>>

    @GET("volumes")
    fun searchBooks(@Query("q") query: String,
                    @Query("key") key: String,
                    @Query("startIndex") startIndex: Int,
                    @Query("maxResults") maxResults: Int
    ): Single<BooksWrapperModel<BookModel>>
}