package com.example.presentation.screens.gameScreen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentGameScreenBinding
import com.example.presentation.screens.base.BaseFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import ru.terrakok.cicerone.android.support.SupportAppScreen

@ExperimentalCoroutinesApi
class GameScreenFragment : BaseFragment(R.layout.fragment_game_screen) {

    private lateinit var binding: FragmentGameScreenBinding
    private lateinit var viewModel: GameScreenViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel()
        binding = FragmentGameScreenBinding.bind(view)
        binding.timer.start(10)


        launchWhenCreated {
            viewModel.stateFlow.collect {
            }
        }

    }


    @Parcelize
    class Screen : SupportAppScreen(), Parcelable {
        override fun getFragment(): Fragment {
            return GameScreenFragment()
        }
    }
}