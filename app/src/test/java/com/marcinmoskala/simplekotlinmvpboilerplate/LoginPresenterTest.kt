package com.marcinmoskala.simplekotlinmvpboilerplate

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.login.LoginPresenter
import com.marcinmoskala.simplekotlinmvpboilerplate.repositories.LoginRepository
import com.marcinmoskala.simplekotlinmvpboilerplate.repositories.LoginResponse
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginPresenterTest {

    fun getMockView(email: String, password: String) = MockLoginView(email, password)

    @Before
    fun setUp() {
        PreferenceHolder.testingMode = true
    }


    @Test
    fun checkBothLoginFieldsEmpty() {
        val mockedView = getMockView("", "")
        val presenter = LoginPresenter(mockedView)
        presenter.attemptLogin()
        checkVaildity(mockedView,
                expectedEmailError = R.string.error_field_required,
                expectedPasswordError = R.string.error_field_required
        )
    }

    @Test
    fun checkBothLoginFieldsErrored() {
        val mockedView = getMockView("MarcinMoskala", "KOKO")
        val presenter = LoginPresenter(mockedView)
        presenter.attemptLogin()
        checkVaildity(mockedView,
                expectedEmailError = R.string.error_invalid_email,
                expectedPasswordError = R.string.error_invalid_password
        )
    }

    @Test
    fun checkEmailFieldErrored() {
        val mockedView = getMockView("MarcinMoskala", "KOKOKOKO")
        val presenter = LoginPresenter(mockedView)
        presenter.attemptLogin()
        checkVaildity(mockedView, expectedEmailError = R.string.error_invalid_email)
    }

    @Test
    fun checkPasswordFieldErrored() {
        val mockedView = getMockView("marcinmoskala@gmail.com", "KOKO")
        val presenter = LoginPresenter(mockedView)
        presenter.attemptLogin()
        checkVaildity(mockedView, expectedPasswordError = R.string.error_invalid_password)
    }

    @Test
    fun networkErrorShowedOnly() {
        LoginRepository.override = object : LoginRepository {
            override fun attemptLogin(email: String, pass: String): Observable<LoginResponse> {
                return Observable.error(Error("Network connection error"))
            }
        }
        val mockedView = getMockView("marcinmoskala@gmail.com", "KOKOKOKO")
        val presenter = LoginPresenter(mockedView)
        presenter.attemptLogin()
        waitUntilAllUnsubscribed(presenter)
        LoginRepository.override = null
        checkVaildity(mockedView, expectedNetworkError = true)
    }

    @Test
    fun correctLoginInformShowedOnly() {
        LoginRepository.override = object : LoginRepository {
            override fun attemptLogin(email: String, pass: String): Observable<LoginResponse> {
                return Observable.just(LoginResponse("SomeToken", User(email)))
            }
        }
        val mockedView = getMockView("marcinmoskala@gmail.com", "KOKOKOKO")
        val presenter = LoginPresenter(mockedView)
        presenter.attemptLogin()
        waitUntilAllUnsubscribed(presenter)
        LoginRepository.override = null
        checkVaildity(mockedView, expectedLoginCorrect = true)
    }

    private fun checkVaildity(
            mockedView: MockLoginView,
            expectedEmailError: Int? = null,
            expectedPasswordError: Int? = null,
            expectedLoginCorrect: Boolean = false,
            expectedNetworkError: Boolean = false
    ) {
        Assert.assertEquals(expectedEmailError, mockedView.emailErrorId)
        Assert.assertEquals(expectedPasswordError, mockedView.passwordErrorId)
        checkNull(expectedLoginCorrect, mockedView.isSuccess)
        checkNull(expectedNetworkError, mockedView.isNetworkErrorInformation)
    }

    private fun checkNull(shouldBeNotnull: Boolean, value: Any?) {
        if (shouldBeNotnull) {
            Assert.assertNotNull(value)
        } else {
            Assert.assertNull(value)
        }
    }

    private fun waitUntilAllUnsubscribed(presenter: LoginPresenter) {
        while (!presenter.subscriptions.all { it.isDisposed })
            Thread.sleep(20)
    }
}