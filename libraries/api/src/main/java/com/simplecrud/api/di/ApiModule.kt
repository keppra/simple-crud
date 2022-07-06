package com.simplecrud.api.di

import android.content.Context
import com.google.gson.Gson
import com.simplecrud.api.BuildConfig
import com.simplecrud.api.interceptors.HelloWorldHeaderInterceptor
import com.simplecrud.api.services.ApiServices
import com.simplecrud.api.services.Services
import com.simplecrud.api.services.ServicesImp
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 10L
private const val DEFAULT_DISK_USAGE_BYTES = 5L * 1024 * 1024
private const val CACHE_NAME = "RETROFIT"

private const val retrofitApiUsers = "retrofitApiUsers"
const val okHttpClientApi = "okHttpClientApi"


val apiModule = module {

    single { provideCache(androidContext()) }

    single(named(retrofitApiUsers)) { provideRetrofit(get(named(okHttpClientApi))) }

    single(named(okHttpClientApi)) { provideOkHttpClient(get(), get()) }

    single<Services> { ServicesImp(get()) }
    single { get<Retrofit>(named(retrofitApiUsers)).create(ApiServices::class.java) }

    single { HelloWorldHeaderInterceptor() }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_USERS)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()


private fun provideOkHttpClient(
    cache: Cache,
    headerInterceptor: HelloWorldHeaderInterceptor
): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .cache(cache)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        //.addInterceptor(headerInterceptor)
        .addInterceptor(interceptor)
        .build()
}

private fun provideCache(context: Context) = Cache(
    File(context.cacheDir, CACHE_NAME), DEFAULT_DISK_USAGE_BYTES
)