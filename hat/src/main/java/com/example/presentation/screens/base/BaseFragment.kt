package com.example.presentation.screens.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.lifecycleScope
import com.example.di.Scopes
import com.example.di.ViewModelFactory
import com.example.presentation.router.AppRouter
import kotlinx.coroutines.CoroutineScope
import toothpick.Toothpick
import javax.inject.Inject


abstract class BaseFragment(constraintLayoutId: Int) : Fragment(constraintLayoutId) {

    @Inject
    lateinit var router: AppRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        val fragmentScope = Toothpick.openScopes(Scopes.ACTIVITY, Scopes.FRAGMENT)
        Toothpick.inject(this, fragmentScope)
        super.onCreate(savedInstanceState)
    }

    fun Fragment.launchWhenCreated(block: suspend CoroutineScope.() -> Unit) {
        this.viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            block.invoke(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(Scopes.FRAGMENT)
    }

    inline fun <reified T : ViewModel> Fragment.obtainViewModel(startAction: (viewModel: T) -> Unit = {}): T {
        val viewModel: T = ViewModelLazy(
            T::class,
            { viewModelStore },
            { Toothpick.openScope(Scopes.APP).getInstance(ViewModelFactory::class.java) }
        ).value
        startAction(viewModel)
        return viewModel
    }

    fun <T> withArgs(argsInit: (bundle: Bundle) -> Unit): T {
        val bundle = Bundle()
        argsInit(bundle)
        this.arguments = bundle
        return this as T
    }
}