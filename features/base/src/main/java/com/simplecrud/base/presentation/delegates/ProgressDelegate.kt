package com.simplecrud.base.presentation.delegates

interface ProgressDelegate {
    fun showProgress(message: String = "")
    fun hideProgress()
}