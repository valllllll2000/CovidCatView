package com.vaxapp.covid19.view.main


interface MainView {

    fun showLoading()
    fun display(response: ViewResponse, town: String)
    fun hideLoading()
    fun showError(error: Throwable)
}