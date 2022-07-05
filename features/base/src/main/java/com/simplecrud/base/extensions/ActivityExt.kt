package com.simplecrud.base.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf

inline fun <reified T : Activity> newActivityInstance(
    context: Context,
    vararg params: Pair<String, Any>
) = Intent(context, T::class.java).apply {
    putExtras(bundleOf(*params))
}