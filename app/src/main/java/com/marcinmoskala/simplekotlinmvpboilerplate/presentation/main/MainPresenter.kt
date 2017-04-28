package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.main

import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.Presenter
import com.marcinmoskala.simplekotlinmvpboilerplate.repositories.Pref

class MainPresenter(val view: MainView, val user: User): Presenter() {

    override fun onStart() {
        val token = Pref.token
        val email = user.email
        view.mainText = "Provided email is $email!\nToken is: $token"
    }
}