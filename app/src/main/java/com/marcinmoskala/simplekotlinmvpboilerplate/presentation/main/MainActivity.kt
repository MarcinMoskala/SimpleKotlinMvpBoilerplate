package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.main

import activitystarter.Arg
import android.os.Bundle
import com.marcinmoskala.simplekotlinmvpboilerplate.R
import com.marcinmoskala.simplekotlinmvpboilerplate.model.User
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.Presenter
import com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common.PresenterBaseActivity
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.bindToText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : PresenterBaseActivity(), MainView {

    @Arg lateinit var user: User
    override val presenter: Presenter by lazy { MainPresenter(this, user) }

    override var mainText: String by bindToText { textView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
