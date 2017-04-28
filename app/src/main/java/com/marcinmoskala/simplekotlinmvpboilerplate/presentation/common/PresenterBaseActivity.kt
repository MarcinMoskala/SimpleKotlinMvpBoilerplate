package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common

import android.os.Bundle
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.toast

abstract class PresenterBaseActivity : BaseActivity(), BaseView {

    abstract val presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showError(e: Throwable) {
        e.message?.let { toast("Error: $it") }
        e.printStackTrace()
        e.cause?.printStackTrace()
    }
}
