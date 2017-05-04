package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.login

import android.os.Bundle
import com.marcinmoskala.kotlinandroidviewbindings.*
import com.marcinmoskala.simplekotlinmvpboilerplate.R
import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.PresenterBaseActivity
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.main.MainActivityStarter

class LoginActivity : PresenterBaseActivity(), LoginView {

    override var progressVisible by bindToLoading(R.id.progressView, R.id.loginFormView)

    override var email: String by bindToEditText(R.id.emailView)
    override val emailRequestFocus by bindToRequestFocus(R.id.emailView)
    override var emailErrorId: Int? by bindToErrorId(R.id.emailView)

    override var password: String by bindToEditText(R.id.passwordView)
    override val passwordRequestFocus by bindToRequestFocus(R.id.passwordView)
    override var passwordErrorId: Int? by bindToErrorId(R.id.passwordView)

    override var loginButtonClickedCallback by bindToClick(R.id.loginButton)

    override val presenter by lazy { LoginPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun pass(user: User) {
        MainActivityStarter.start(this, user)
    }
}