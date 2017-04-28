package com.marcinmoskala.simplekotlinmvpboilerplate.utills

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applySchedulers(): Observable<T> =
        subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.smartSubscribe(
        onStart: (() -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null,
        onFinish: (() -> Unit)? = null,
        onSuccess: (T) -> Unit = {}): Disposable =
        addStartFinishActions(onStart, onFinish)
                .subscribe(onSuccess, { onError?.invoke(it) })

fun <T> Observable<T>.addStartFinishActions(onStart: (() -> Unit)? = null, onFinish: (() -> Unit)? = null): Observable<T> {
    onStart?.invoke()
    return doOnTerminate({ onFinish?.invoke() })
}

fun <T> Single<T>.applySchedulers(): Single<T> =
        subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Single<T>.smartSubscribe(
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onStart: (() -> Unit)? = null,
        onFinish: (() -> Unit)? = null
): Disposable {
    onStart?.invoke()
    val withOnFinish = if (onFinish != null) doFinally { onFinish() } else this
    return withOnFinish.subscribe(onSuccess, onError)
}