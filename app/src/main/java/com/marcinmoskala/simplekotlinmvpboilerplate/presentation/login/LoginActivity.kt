package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.login

import android.os.Bundle
import android.view.View
import com.marcinmoskala.simplekotlinmvpboilerplate.R
import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.PresenterBaseActivity
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.main.MainActivityStarter
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.bindToEditText
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.bindToErrorId
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.properties.Delegates

class LoginActivity : PresenterBaseActivity(), LoginView {

    override var progressVisible by Delegates.observable(false) { _, _, n ->
        progressView.visibility = if (n) View.VISIBLE else View.GONE
        loginFormView.visibility = if (n) View.GONE else View.VISIBLE
    }
    override var email: String by bindToEditText { emailView }
    override var password: String by bindToEditText { passwordView }
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

    override fun pass(user: User) {
        MainActivityStarter.start(this, user)
    }
}