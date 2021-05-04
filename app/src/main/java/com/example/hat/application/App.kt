package com.example.hat.application

import android.app.Application
import com.example.di.Scopes
import com.example.di.AppModules
import toothpick.Toothpick

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val appScope = Toothpick.openScope(Scopes.APP)
        appScope.installModules(AppModules(this))
    }

}