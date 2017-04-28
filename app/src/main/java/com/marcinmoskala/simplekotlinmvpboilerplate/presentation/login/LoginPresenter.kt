package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.login

import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.Presenter
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.applySchedulers
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.smartSubscribe

class LoginPresenter(val view: LoginView): Presenter() {

    val loginUseCase by lazy { LoginUseCase() }
    val validateLoginFieldsUseCase by lazy { ValidateLoginFieldsUseCase() }

    fun attemptLogin() {
        val (email, password) = view.email to view.password
        subscriptions += validateLoginFieldsUseCase.validateLogin(email, password)
                .smartSubscribe { (emailErrorId, passwordErrorId) ->
                    view.passwordErrorId = passwordErrorId
                    view.emailErrorId = emailErrorId
                    when {
                        emailErrorId != null -> view.requestEmailFocus()
                        passwordErrorId != null -> view.requestPasswordFocus()
                        else -> sendLoginRequest(email, password)
                    }
                }
    }

    private fun sendLoginRequest(email: String, password: String) {
        loginUseCase.sendLoginRequest(email, password)
                .applySchedulers()
                .smartSubscribe(
                        onStart = { view.progressVisible = true },
                        onSuccess = { (token) -> view.informAboutLoginSuccess(token) },
                        onError = view::showError,
                        onFinish = { view.progressVisible = false }
                )
    }
}