package com.simplecrud.base.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel: ViewModel() {

    // Variables //

    private val disposables: CompositeDisposable = CompositeDisposable()

    // LifeCycle //

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    // Public methods //

    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun onDestroyDisposables() {
        disposables.dispose()
    }
}