package com.simplecrud.utils.extensions

inline val <reified T> T.TAG: String get() = T::class.java.simpleName