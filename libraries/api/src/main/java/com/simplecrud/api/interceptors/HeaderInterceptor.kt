package com.simplecrud.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent

class HeaderInterceptor: Interceptor, KoinComponent {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader(name = "Content-Type", value = "application/json")
                .addHeader(name = "Accept", value = "application/json")
                .addHeader(name = "Content-Length", value = chain.request().body?.contentLength().toString())
                .build()
        )
    }

}