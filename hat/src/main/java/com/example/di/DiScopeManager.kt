package com.example.di

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class DiScopeManager @Inject constructor() {

    private val appScope: Scope = Toothpick.openScope("APP")

    fun installScope(app: Application) {
        Toothpick.inject(app, appScope)
    }

    fun installScope(fragment: Fragment) {
        Toothpick.inject(fragment, appScope)
    }

    fun installScope(activity: Activity) {

        Toothpick.inject(activity, appScope)
    }

    fun closeAppScope() {
        Toothpick.closeScope("APP")
    }

    fun closeActivityScope() {
        Toothpick.closeScope("APP")
    }
}