package com.simplecrud.base.presentation

import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.scope.ScopeFragment

abstract class BaseFragment: ScopeFragment() {

    // Variables //

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    // Lifecycle //

    override fun onStop() {
        subscriptions.clear()
        super.onStop()
    }

    override fun onDestroy() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
        super.onDestroy()
    }

}