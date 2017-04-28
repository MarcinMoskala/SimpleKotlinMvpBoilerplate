package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.login

import android.os.Bundle
import android.view.View
import com.marcinmoskala.simplekotlinmvpboilerplate.R
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.PresenterBaseActivity
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.bindToErrorId
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.bindToText
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.properties.Delegates

class LoginActivity : PresenterBaseActivity(), LoginView {

    override var progressVisible by Delegates.observable(false) { _, _, n ->
        progressView.visibility = if (n) View.VISIBLE else View.GONE
        loginFormView.visibility = if (n) View.GONE else View.VISIBLE
    }
    override var email: String by bindToText { emailView }
    override var password: String by bindToText { passwordView }
    override var emailErrorId: Int? by bindToErrorId { emailView }
    override var passwordErrorId: Int? by bindToErrorId { passwordView }

    override val presenter by lazy { LoginPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener { presenter.attemptLogin() }
    }

    override fun requestEmailFocus() {
        emailView.requestFocus()
    }

    override fun requestPasswordFocus() {
        passwordView.requestFocus()
    }

    override fun informAboutLoginSuccess(token: String) {
        toast("Login succeed. Token: $token")
    }
}

