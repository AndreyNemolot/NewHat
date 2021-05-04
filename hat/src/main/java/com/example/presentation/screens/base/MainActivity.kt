package com.example.presentation.screens.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProviders
import com.example.presentation.R
import com.example.di.Scopes
import com.example.di.ViewModelFactory
import com.example.di.ActivityModules
import com.example.presentation.router.AppRouter
import com.example.presentation.screens.mainScreen.MainScreenFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject


@ExperimentalCoroutinesApi
open class MainActivity : AppCompatActivity() {

//    private val scopeManager = DiScopeManager()

    private val navigator: Navigator = SupportAppNavigator(this, R.id.fragmentContainer)

    @Inject
    lateinit var router: AppRouter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder



    override fun onCreate(savedInstanceState: Bundle?) {
        val activityScope = Toothpick.openScopes(Scopes.APP, Scopes.ACTIVITY)
        activityScope.installModules(ActivityModules())
        Toothpick.inject(this, activityScope)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        if (savedInstanceState == null) {
//            router.newRootChain(MainScreenFragment.Screen())
//        }


    }

//    private fun updateState(screenState: MainState?) {
//        println("UPDAATES")
//        when(screenState) {
//            is MainState.Start -> {
//            }
//        }
//    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onDestroy() {
        Toothpick.closeScope(Scopes.ACTIVITY)
        super.onDestroy()
    }

    inline fun <reified T : ViewModel> AppCompatActivity.obtainViewModel(): T =
        ViewModelLazy(
            T::class,
            { viewModelStore },
            { Toothpick.openScope(Scopes.APP).getInstance(ViewModelFactory::class.java) }
        ).value
}