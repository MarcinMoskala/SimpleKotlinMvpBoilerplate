package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common

interface BaseView {
    fun showError(e: Throwable)
    fun showErrorAndFinish(e: Throwable)
}