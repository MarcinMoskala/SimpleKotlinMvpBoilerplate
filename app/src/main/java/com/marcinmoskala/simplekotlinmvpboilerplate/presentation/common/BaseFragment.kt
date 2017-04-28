package com.marcinmoskala.simplekotlinmvpboilerplate.presentation.common

import activitystarter.ActivityStarter
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment(@LayoutRes private val layoutId: Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ActivityStarter.fill(this)
        return inflater.inflate(layoutId, container, false)
    }
}
