package com.vaxapp.covid19.view.main


interface MainView {

    fun showLoading()
    fun display(response: ViewResponse)
    fun hideLoading()
    fun showError(error: Throwable)
}