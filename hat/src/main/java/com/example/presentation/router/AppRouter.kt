package com.example.presentation.router

import ru.terrakok.cicerone.Screen

interface AppRouter {
    fun navigateTo(screen: Screen)
    fun replace(screen: Screen)
    fun newRootChain(screen: Screen)
    fun exit()
}