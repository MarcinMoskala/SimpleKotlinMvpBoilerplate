package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.login

import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.RxPresenter
import com.marcinmoskala.simplekotlinmvpboilerplate.repositories.Pref
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.applySchedulers
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.smartSubscribe

class LoginPresenter(val view: LoginView) : RxPresenter() {

    private val loginUseCase by lazy { LoginUseCase() }
    private val validateLoginFieldsUseCase by lazy { ValidateLoginFieldsUseCase() }

    override fun onStart() {
        view.loginButtonClickedCallback = { attemptLogin() }
    }

    private fun attemptLogin() {
        val (email, password) = view.email to view.password
        subscriptions += validateLoginFieldsUseCase.validateLogin(email, password)
                .smartSubscribe(
                        onError = view::showError,
                        onSuccess = { (emailErrorId, passwordErrorId) -> onValidationResult(email, emailErrorId, password, passwordErrorId) }
                )
    }

    private fun onValidationResult(email: String, emailErrorId: Int?, password: String, passwordErrorId: Int?) {
        view.passwordErrorId = passwordErrorId
        view.emailErrorId = emailErrorId
        when {
            emailErrorId != null -> view.emailRequestFocus()
            passwordErrorId != null -> view.passwordRequestFocus()
            else -> sendLoginRequest(email, password)
        }
    }

    private fun sendLoginRequest(email: String, password: String) {
        subscriptions += loginUseCase.sendLoginRequest(email, password)
                .applySchedulers()
                .smartSubscribe(
                        onStart = { view.progressVisible = true },
                        onSuccess = { (token, user) ->
                            Pref.token = token
                            view.pass(user)
                        },
                        onError = view::showError,
                        onFinish = { view.progressVisible = false }
                )
    }
}