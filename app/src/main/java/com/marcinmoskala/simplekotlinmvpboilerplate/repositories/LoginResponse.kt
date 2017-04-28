package com.marcinmoskala.simplekotlinmvpboilerplate.repositories

import com.marcinmoskala.simplekotlinmvpboilerplate.model.User

data class LoginResponse(val token: String, val user: User)