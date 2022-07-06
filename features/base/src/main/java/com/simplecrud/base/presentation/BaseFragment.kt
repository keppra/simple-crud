package com.simplecrud.base.presentation

import android.content.Context
import com.simplecrud.base.presentation.delegates.ProgressDelegate
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.scope.ScopeFragment

abstract class BaseFragment: ScopeFragment() {

    // Variables //

    private val subscriptions: CompositeDisposable = CompositeDisposable()
    protected var progressDelegate: ProgressDelegate? = null

    // Lifecycle //

    override fun onAttach(context: Context) {
        super.onAttach(context)
        progressDelegate = activity as? ProgressDelegate
    }

    override fun onStop() {
        subscriptions.clear()
        super.onStop()
    }

    override fun onDestroy() {
        if (!subscriptions.isDisposed) {
            subscriptions.dispose()
        }
        progressDelegate = null
        super.onDestroy()
    }

    // Progress //

    protected fun showProgress(showProgress: Boolean) {
        if (showProgress) {
            progressDelegate?.showProgress()
        } else {
            progressDelegate?.hideProgress()
        }
    }

}