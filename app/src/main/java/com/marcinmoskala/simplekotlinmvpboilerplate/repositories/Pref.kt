package com.marcinmoskala.simplekotlinmvpboilerplate.repositories

import com.marcinmoskala.kotlinpreferences.PreferenceHolder

object Pref: PreferenceHolder() {
    var token: String? by bindToPreferenceFieldNullable("TokenKey")
}