package com.example.presentation.commandsScreen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.commandsScreen.epoxy.EpoxyCommandsController
import com.example.presentation.commonView.CommonItemDecoration
import com.example.presentation.databinding.FragmentCommandsScreenBinding
import com.example.presentation.screens.base.BaseFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.terrakok.cicerone.android.support.SupportAppScreen

@ExperimentalCoroutinesApi
class CommandsScreenFragment : BaseFragment(R.layout.fragment_commands_screen) {

    private lateinit var binding: FragmentCommandsScreenBinding
    private lateinit var viewModel: CommandsScreenViewModel

    private val controller = EpoxyCommandsController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCommandsScreenBinding.bind(view)
        viewModel = obtainViewModel()
        binding.recycleView.setController(controller)
        binding.recycleView.addItemDecoration(CommonItemDecoration(8f, 8f))

        launchWhenCreated {
            viewModel.stateFlow.onEach {
                updateState(it)
            }.launchIn(this)
//            viewModel.commandFlow.onEach {
//                handleCommand(it)
//            }.launchIn(this)
        }

        binding.toolbar.setNavigationOnClickListener {
            router.exit()
        }
        binding.toolbar.title = "COMMANDS"

    }

//    private fun handleCommand(playerCommand: AddPlayerCommand) {
//        when (playerCommand) {
//        }
//    }

    private fun updateState(state: CommandScreenState) {
        controller.setData(state)
    }

    @Parcelize
    class Screen : SupportAppScreen(), Parcelable {
        override fun getFragment(): Fragment {
            return CommandsScreenFragment()
        }
    }

}