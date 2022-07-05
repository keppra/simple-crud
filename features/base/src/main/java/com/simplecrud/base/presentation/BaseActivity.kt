package com.simplecrud.base.presentation

import android.os.Bundle
import android.util.Log
import com.simplecrud.base.navigation.NavigationActivityStack
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity

abstract class BaseActivity: ScopeActivity() {

    // Navigation //

    private val navigationStack: NavigationActivityStack by inject()

    // Lifecycle //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isTaskRoot) {
            navigationStack.clearStack()
            Log.d("TAG", "Actividad actual: $this")
        }
        navigationStack.addActivityToStack(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationStack.deleteActivityFromStack(this)
    }

}