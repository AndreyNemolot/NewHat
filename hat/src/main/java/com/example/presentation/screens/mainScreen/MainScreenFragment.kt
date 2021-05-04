package com.example.presentation.screens.mainScreen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentMainScreenBinding
import com.example.presentation.screens.gameConfigScreen.GameConfigScreenFragment
import com.example.presentation.screens.base.BaseFragment
import com.example.presentation.screens.mainScreen.epoxy.EpoxyMainController
import com.example.presentation.screens.mainScreen.epoxy.MainFragmentItemDecoration
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import ru.terrakok.cicerone.android.support.SupportAppScreen

@ExperimentalCoroutinesApi
class MainScreenFragment : BaseFragment(R.layout.fragment_main_screen),
    EpoxyMainController.MainMenu {

    private lateinit var binding: FragmentMainScreenBinding

    private lateinit var viewModel: MainScreenViewModel
    private val controller = EpoxyMainController(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainScreenBinding.bind(view)
        viewModel = obtainViewModel()
        launchWhenCreated {
            viewModel.stateFlow.collect {
                updateState(it)
            }
        }

        binding.list.setController(controller)
        binding.list.addItemDecoration(MainFragmentItemDecoration())
    }

    private fun updateState(state: ScreenState) {
        controller.setData(state.menuList)
    }

    @Parcelize
    class Screen : SupportAppScreen(), Parcelable {
        override fun getFragment(): Fragment {
            return MainScreenFragment()
        }
    }

    override fun openNewGame() {
        router.navigateTo(GameConfigScreenFragment.Screen())
    }
}