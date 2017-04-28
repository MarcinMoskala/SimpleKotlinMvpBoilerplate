package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.login

import com.marcinmoskala.simplekotlinmvpboilerplate.network.LoginRepository

class LoginUseCase {

    val loginRepository by LoginRepository.lazyGet()

    fun sendLoginRequest(email: String, password: String) = loginRepository
            .attemptLogin(email, password)
}