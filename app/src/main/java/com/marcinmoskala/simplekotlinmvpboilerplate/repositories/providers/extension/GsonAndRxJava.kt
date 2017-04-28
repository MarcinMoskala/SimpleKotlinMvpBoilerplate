package com.marcinmoskala.simplekotlinmvpboilerplate.repositories.providers.extension

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

fun Retrofit.Builder.addConverters(): Retrofit.Builder = this
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())


