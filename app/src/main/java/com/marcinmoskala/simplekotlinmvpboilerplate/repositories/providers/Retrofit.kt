package com.marcinmoskala.simplekotlinmvpboilerplate.repositories.providers

import com.marcinmoskala.simplekotlinmvpboilerplate.BuildConfig
import com.marcinmoskala.simplekotlinmvpboilerplate.repositories.Pref
import com.marcinmoskala.simplekotlinmvpboilerplate.repositories.providers.extension.addConverters
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object UnauthenticatedApi : Provider<Retrofit>({
    makeRetrofit()
})

object OAuthRetrofit : Provider<Retrofit>({
    makeRetrofit(accessTokenProvidingInterceptor())
})

fun makeRetrofit(vararg interceptors: Interceptor) = Retrofit.Builder()
        .baseUrl("https://api.someexample.com/")
        .client(makeHttpClient(interceptors))
        .addConverters()
        .build()

private fun makeHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())
        .apply { interceptors().addAll(interceptors) }
        .addInterceptor(loggingInterceptor())
        .build()

fun accessTokenProvidingInterceptor() = Interceptor { chain ->
    val accessToken = Pref.token
    chain.proceed(chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build())
}

fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}

fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Accept-Language", "en")
            .addHeader("Content-Type", "application/json")
            .build())
}