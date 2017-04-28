package com.marcinmoskala.simplekotlinmvpboilerplate.model

import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

@PaperParcel
class User(val email: String): PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelable.Creator(User::class.java)
    }
}