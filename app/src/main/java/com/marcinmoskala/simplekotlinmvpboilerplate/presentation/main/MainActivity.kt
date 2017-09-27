package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.main

import activitystarter.Arg
import android.os.Bundle
import com.marcinmoskala.kotlinandroidviewbindings.bindToTextView
import com.marcinmoskala.simplekotlinmvpboilerplate.R
import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.Presenter
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.PresenterBaseActivity

class MainActivity : PresenterBaseActivity(), MainView {

    @Arg lateinit var user: User
    val mainPresenter: Presenter by presenter { MainPresenter(this, user) }

    override var mainText: String by bindToTextView(R.id.textView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
