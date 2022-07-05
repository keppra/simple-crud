package com.simplecrud.base.extensions

import android.content.ComponentCallbacks
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier

inline fun <reified T : Any> ScopeActivity.injectWithActivityAsParam(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED
) = inject<T>(qualifier, mode) { parametersOf(this) }


inline fun <reified T : Any> View.injectFromActivity(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: ParametersDefinition? = null
): Lazy<T?> {
    return lazy(mode) {
        (context as? ComponentCallbacks)?.get(qualifier, parameters)
    }
}

inline fun <reified T : Any> Fragment.injectFromActivity(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
    noinline parameters: ParametersDefinition? = { parametersOf(requireActivity()) }
) = lazy(mode) {
    requireActivity().get<T>(qualifier, parameters)
}