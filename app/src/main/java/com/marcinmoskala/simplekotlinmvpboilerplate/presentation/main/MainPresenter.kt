package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.main

import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.Presenter

class MainPresenter(val view: MainView, val token: String): Presenter() {

    override fun onStart() {
        view.mainText = "Token is $token!"
    }
}