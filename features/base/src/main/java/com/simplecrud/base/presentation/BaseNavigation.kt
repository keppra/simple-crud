package com.simplecrud.base.presentation

import android.os.Bundle

interface BaseNavigation {
    // All //
    fun goBack()
    // Activities //
    fun backToMain()
    fun<T> goBackTo(activityClass: Class<T>)
}