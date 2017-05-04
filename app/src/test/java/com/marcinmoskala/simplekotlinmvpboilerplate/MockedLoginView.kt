package com.marcinmoskala.simplekotlinmvpboilerplate

import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.login.LoginView

class MockedLoginView(
        override var email: String = "",
        override var password: String = "",
        override var progressVisible: Boolean = false,
        override var emailErrorId: Int? = null,
        override var passwordErrorId: Int? = null,
        override var loginButtonClickedCallback: () -> Unit = {}
) : LoginView {

    var focus: PossibleLoginFocus = PossibleLoginFocus.NONE
    var isSuccess: Boolean? = null
    var isNetworkErrorInformation: String? = null

    override val emailRequestFocus: () -> Unit = {
        focus = PossibleLoginFocus.EMAIL

    }
    override val passwordRequestFocus: () -> Unit = {
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