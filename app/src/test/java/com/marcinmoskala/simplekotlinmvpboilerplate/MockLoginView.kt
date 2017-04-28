package com.marcinmoskala.simplekotlinmvpboilerplate

import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.login.LoginView

class MockLoginView(
        override var email: String,
        override var password: String,
        override var progressVisible: Boolean = false,
        override var emailErrorId: Int? = null,
        override var passwordErrorId: Int? = null
) : LoginView {

    var focus: PossibleLoginFocus = PossibleLoginFocus.NONE
    var isSuccess: Boolean? = null
    var isNetworkErrorInformation: String? = null

    override fun requestEmailFocus() {
        focus = PossibleLoginFocus.EMAIL
    }

    override fun requestPasswordFocus() {
        focus = PossibleLoginFocus.PASS
    }

    override fun showError(e: Throwable) {
        isNetworkErrorInformation = e.message
    }

    override fun pass(user: User) {
        isSuccess = true
    }

    enum class PossibleLoginFocus {NONE, EMAIL, PASS }
}