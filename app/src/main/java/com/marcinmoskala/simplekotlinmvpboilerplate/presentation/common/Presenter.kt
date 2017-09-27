package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common

interface Presenter {

    fun onCreate() {}

    fun onStart() {}

    fun onDestroy() {}

    fun onStop() {}

    fun onResume() {}
}