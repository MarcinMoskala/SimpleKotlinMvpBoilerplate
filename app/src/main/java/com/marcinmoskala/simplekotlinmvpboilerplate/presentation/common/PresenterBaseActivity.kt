package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common

import android.os.Bundle
import com.marcinmoskala.simplekotlinmvpboilerplate.utills.toast

abstract class PresenterBaseActivity : BaseActivity(), BaseView {

    fun presenter(init: () -> Presenter) = lazy { init() }
            .also { lazyPresenters += it }

    private var lazyPresenters: List<Lazy<Presenter>> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lazyPresenters.forEach { it.value.onCreate() }
    }

    override fun onStart() {
        super.onStart()
        lazyPresenters.forEach { it.value.onStart() }
    }

    override fun onResume() {
        super.onResume()
        lazyPresenters.forEach { it.value.onResume() }
    }

    override fun onStop() {
        super.onStop()
        lazyPresenters.forEach { it.value.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        lazyPresenters.forEach { it.value.onDestroy() }
    }

    override fun showError(e: Throwable) {
        e.message?.let { toast("Error: $it") }
        e.printStackTrace()
        e.cause?.printStackTrace()
    }
}
