package com.example.presentation.screens.gameConfigScreen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentGameConfigScreenBinding
import com.example.presentation.screens.addPeopleScreen.AddPeopleScreenFragment
import com.example.presentation.screens.addWordsScreen.AddWordsScreenFragment
import com.example.presentation.screens.base.BaseFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import ru.terrakok.cicerone.android.support.SupportAppScreen

@ExperimentalCoroutinesApi
class GameConfigScreenFragment : BaseFragment(R.layout.fragment_game_config_screen) {

    private lateinit var binding: FragmentGameConfigScreenBinding

    private lateinit var viewModel: GameConfigScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel()
        binding = FragmentGameConfigScreenBinding.bind(view)

//        binding.toolbar.navigationIcon = getDrawableCompat(android.R.drawable.)
        binding.toolbar.setNavigationOnClickListener {
            router.exit()
        }

        binding.people.setOnClickListener {
            router.navigateTo(AddPeopleScreenFragment.Screen())
        }

        binding.word.setOnClickListener {
            router.navigateTo(AddWordsScreenFragment.Screen(""))
        }

        binding.toolbar.title = "SETTINGS"

        launchWhenCreated {
            viewModel.stateFlow.collect {
            }
        }

    }


    @Parcelize
    class Screen : SupportAppScreen(), Parcelable {
        override fun getFragment(): Fragment {
            return GameConfigScreenFragment()
        }
    }
}