package com.marcinmoskala.simplekotlinmvpboilerplate.repositories

import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.repositories.providers.Provider
import io.reactivex.Observable

interface LoginRepository {

    fun attemptLogin(email: String, pass: String): Observable<LoginResponse>

    class MockLoginRepository : LoginRepository {
        override fun attemptLogin(email: String, pass: String): Observable<LoginResponse> = when {
            email.endsWith(".pl") -> throw Error("Invalid Email")
            else -> Observable.just(LoginResponse("TokenToken", User(email)))
        }
    }

    companion object : Provider<LoginRepository>({ MockLoginRepository() })
}