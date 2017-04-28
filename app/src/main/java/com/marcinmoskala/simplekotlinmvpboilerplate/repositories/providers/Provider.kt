package com.marcinmoskala.simplekotlinmvpboilerplate.repositories.providers

abstract class Provider<T>(val initializer: () -> T) {

    var override: T? = null
    var original: T? = null

    fun get(): T = override ?: original ?: initializer().apply { original = this }
    fun lazyGet(): Lazy<T> = lazy { get() }
}
