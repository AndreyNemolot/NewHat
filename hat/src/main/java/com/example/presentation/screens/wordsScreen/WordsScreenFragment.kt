package com.example.presentation.screens.wordsScreen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.example.domain.model.Player
import com.example.presentation.R
import com.example.presentation.databinding.FragmentWordsScreenBinding
import com.example.presentation.screens.base.BaseFragment
import com.example.presentation.screens.wordsScreen.epoxy.EpoxyWordsController
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.terrakok.cicerone.android.support.SupportAppScreen

@ExperimentalCoroutinesApi
class WordsScreenFragment : BaseFragment(R.layout.fragment_words_screen),
    EpoxyWordsController.WordsPlayerListener {

    private lateinit var binding: FragmentWordsScreenBinding

    private lateinit var viewModel: WordsScreenViewModel

    private val controller = EpoxyWordsController(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWordsScreenBinding.bind(view)
        viewModel = obtainViewModel()
        binding.recycleView.setController(controller)


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
        binding.toolbar.title = "WORDS"

    }

//    private fun handleCommand(playerCommand: AddPlayerCommand) {
//        when (playerCommand) {
//            AddPlayerCommand.RemovePlayerError -> {}
//            AddPlayerCommand.AddPlayerError -> {}
//        }
//    }


    private fun updateState(stateAddPeople: WordsScreenState) {
        controller.setData(stateAddPeople)
    }

    @Parcelize
    class Screen : SupportAppScreen(), Parcelable {
        override fun getFragment(): Fragment {
            return WordsScreenFragment()
        }
    }

    override fun openWordsScreen(player: Player) {
        TODO("Not yet implemented")
    }

    override fun changeWordsRestrition(count: Int) {
        viewModel.setRestriction(count)
    }

}