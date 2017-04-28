package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common

import android.support.annotation.LayoutRes

abstract class PresenterBaseFragment(@LayoutRes layoutId: Int) : BaseFragment(layoutId) {

    abstract val presenter: Presenter

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}
