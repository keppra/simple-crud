package com.simplecrud.base.domain.extensions

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import com.simplecrud.base.domain.entities.Result

fun <T> Single<T>.dispatchers(): Single<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun Completable.dispatchers(): Completable =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.dispatchers(): Observable<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.toResult(): Single<Result<T>> =
    this.map { Result.Success(it) as Result<T> }
        .onErrorReturn { exception ->
            Result.Failure(exception)
        }

fun <T> Observable<T>.toResult(): Observable<Result<T>> =
    this.map { Result.Success(it) as Result<T> }
        .onErrorReturn { exception ->
            Result.Failure(exception)
        }