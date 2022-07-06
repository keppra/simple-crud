package com.simplecrud.commonui.extensions

import android.text.Editable
import android.text.SpannableStringBuilder

fun String.toEditable(): Editable = SpannableStringBuilder(this)