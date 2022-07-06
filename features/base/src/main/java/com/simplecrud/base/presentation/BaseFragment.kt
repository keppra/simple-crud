package com.simplecrud.base.presentation

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import com.example.commonui.R
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

    // Dialogs //

    protected fun renderInformativeDialog(title: String, details: String, buttonText: String) {
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.custom_dialog_layout, null)
        val dialog = AlertDialog.Builder(activity).setView(dialogView)
        val customDialog = dialog.show()
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val tvTitle = dialogView.findViewById<TextView>(R.id.tv_title)
        val tvDetails = dialogView.findViewById<TextView>(R.id.tv_detail)
        val btClose = dialogView.findViewById<Button>(R.id.button)
        tvTitle.text = title
        tvDetails.text = details
        btClose.text = buttonText

        btClose.setOnClickListener {
            customDialog.dismiss()
        }
    }

}