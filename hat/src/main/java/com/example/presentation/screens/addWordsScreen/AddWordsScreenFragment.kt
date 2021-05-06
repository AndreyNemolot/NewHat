package com.example.presentation.screens.addWordsScreen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.commonView.CommonItemDecoration
import com.example.presentation.commonView.WordPopup
import com.example.presentation.databinding.FragmentAddWordsScreenBinding
import com.example.presentation.screens.addWordsScreen.epoxy.EpoxyWordController
import com.example.presentation.screens.addWordsScreen.epoxy.SafeFlexboxLayoutManager
import com.example.presentation.screens.base.BaseFragment
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.JustifyContent
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import ru.terrakok.cicerone.android.support.SupportAppScreen

@ExperimentalCoroutinesApi
class AddWordsScreenFragment : BaseFragment(R.layout.fragment_add_words_screen),
    WordPopup.PopupListener {

    private lateinit var binding: FragmentAddWordsScreenBinding
    private lateinit var viewModel: AddWordsScreenViewModel
    private val controller = EpoxyWordController(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddWordsScreenBinding.bind(view)
        viewModel = obtainViewModel {
            val playerName = requireArguments().getString(PLAYER_NAME)
            it.initialize(requireNotNull(playerName))
        }

        launchWhenCreated {
            viewModel.stateFlow.collect {
                updateState(it)
            }
        }

        binding.addButton.setOnClickListener {
            addWord(binding.textInputEditText.text.toString())
        }

        val lm = SafeFlexboxLayoutManager(requireContext()).also {
            it.justifyContent = JustifyContent.FLEX_START
            it.flexWrap = FlexWrap.WRAP
        }
        binding.recycleView.apply {
            layoutManager = lm
            addItemDecoration(CommonItemDecoration(6f, 8f))
            setController(controller)
        }

        binding.textInputEditText.setOnEditorActionListener { v, actionId, event ->
            when(actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    addWord(binding.textInputEditText.text.toString())
                    true
                }
                else -> false
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            router.exit()
        }
        binding.toolbar.title = "WORDS"
    }

    private fun addWord(word: String) {
        viewModel.addPeople(word)
        binding.textInputEditText.text?.clear()
    }

    private fun updateState(stateAddWord: AddWordScreenState) {
        controller.setData(stateAddWord.wordList)
    }

    override fun remove(people: String) {
        viewModel.removePeople(people)
    }

    override fun edit(people: String) {
        binding.textInputEditText.setText(people)
        viewModel.removePeople(people)
    }

    @Parcelize
    class Screen(private val playerName: String) : SupportAppScreen(), Parcelable {
        override fun getFragment(): Fragment {
            return AddWordsScreenFragment().withArgs {
                it.putString(PLAYER_NAME, playerName)
            }
        }
    }

    companion object {
        private const val PLAYER_NAME = "PLAYER_NAME"
    }
}