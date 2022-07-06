package com.simplecrud.commonui.state

interface ErrorState<T> {
    val code: T
    val errorMessage: String
}

interface ErrorTypeState<T> {
    fun getTypeError(code: Int): T
}