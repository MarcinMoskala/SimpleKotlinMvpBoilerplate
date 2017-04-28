package com.marcinmoskala.simplekotlinmvpboilerplate

import com.marcinmoskala.kotlinpreferences.PreferenceHolder
import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.main.MainPresenter
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.main.MainView
import com.marcinmoskala.simplekotlinmvpboilerplate.repositories.Pref
import org.junit.Before
import org.junit.Test



class MainPresenterTest {

    @Before
    fun setUp() {
        PreferenceHolder.testingMode = true
    }

    @Test
    fun checkEmailDisplayed() {
        val someEmail = "email@koko.pl"

        val view: MainView = object : MainView {
            override var mainText: String = ""
        }

        val user: User = User(someEmail)
        val presenter = MainPresenter(view, user)

        presenter.onStart()
        assertContains(someEmail, view.mainText)
    }

    @Test
    fun checkTokenDisplayed() {
        val someToken = "SomeToken"
        Pref.token = someToken

        val view: MainView = object : MainView {
            override var mainText: String = ""
        }

        val presenter = MainPresenter(view, User("email@koko.pl"))

        presenter.onStart()
        assertContains(someToken, view.mainText)
    }

    fun assertContains(subtext: String, text: String) {
        assert(subtext in text) { "Text: $text does not contain $subtext" }
    }
}