package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxPresenter: Presenter {

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        add(disposable)
    }

    var subscriptions = CompositeDisposable()

    override fun onDestroy() {
        subscriptions.dispose()
    }
}