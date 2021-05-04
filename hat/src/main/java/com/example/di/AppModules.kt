package com.example.di

import android.app.Application
import com.example.data.people.PeopleRepository
import com.example.data.words.WordRepository
import com.example.domain.interactor.PlayerInteractor
import com.example.domain.interactor.WordInteractor
import toothpick.config.Module
import toothpick.ktp.binding.bind

class AppModules(application: Application) : Module() {

    init {
        this.bind(Application::class.java).toInstance(application)
        bind<PlayerInteractor>().singleton()
        bind<PeopleRepository>().singleton()
        bind<WordRepository>().singleton()
        bind<WordInteractor>().singleton()
    }
}