package com.simplecrud.base.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.simplecrud.base.navigation.NavigationActivityStack
import com.simplecrud.base.presentation.delegates.ProgressDelegate
import com.simplecrud.kprogresshud.KProgressHUD
import com.example.commonui.R
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity

abstract class BaseActivity: ScopeActivity(), ProgressDelegate {

    // Views //

    protected var progressHud: KProgressHUD? = null

    // Navigation //

    private val navigationStack: NavigationActivityStack by inject()

    // Lifecycle //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isTaskRoot) {
            navigationStack.clearStack()
            Log.d("TAG", "Actividad actual: $this")
        }
        prepareProgressHud()
        navigationStack.addActivityToStack(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationStack.deleteActivityFromStack(this)
    }

    // Progres hud //

    private fun prepareProgressHud() {
        progressHud = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setDimAmount(0.6f)
            .setCancellable(false)
            .setMaxProgress(100)
    }

    // Progress delegate //

    override fun showProgress(message: String) {
        if (message.isNotEmpty()) {
            progressHud?.setLabel(message)
        } else {
            progressHud?.setLabel(getString(R.string.loading))
        }
        progressHud?.show()
    }

    override fun hideProgress() {
        progressHud?.dismiss()
    }


}