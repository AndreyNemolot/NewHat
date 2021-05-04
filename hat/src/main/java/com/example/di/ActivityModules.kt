package com.example.di

import com.example.data.people.PeopleRepository
import com.example.domain.interactor.PlayerInteractor
import com.example.presentation.router.AppRouter
import com.example.presentation.router.AppRouterImpl
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import toothpick.config.Module
import toothpick.ktp.binding.bind

class ActivityModules : Module() {

    init {
        val cicerone = Cicerone.create()
        bind<Router>().toInstance(cicerone.router)
        bind<NavigatorHolder>().toInstance(cicerone.navigatorHolder)
        bind(AppRouter::class.java).to(AppRouterImpl::class.java).singleton()
        bind<ViewModelFactory>().singleton()
    }
}