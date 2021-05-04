package com.example.presentation.screens.addWordsScreen

import android.os.Bundle
import android.os.Parcelable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.commonView.WordPopup
import com.example.presentation.databinding.FragmentAddWordsScreenBinding
import com.example.presentation.screens.base.BaseFragment
import com.example.utilites.dpToPix
import com.example.utilites.getColor
import com.example.utilites.getDrawable
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import ru.terrakok.cicerone.android.support.SupportAppScreen

@ExperimentalCoroutinesApi
class AddWordsScreenFragment : BaseFragment(R.layout.fragment_add_words_screen),
    WordPopup.PopupListener {

    private lateinit var binding: FragmentAddWordsScreenBinding
    private lateinit var viewModel: AddWordsScreenViewModel

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
        binding.flexBox.removeAllViews()
        stateAddWord.wordList.forEach {
            addWordToFlexBox(it.word)
        }
    }

    private fun addWordToFlexBox(people: String) {
        val word = TextView(requireContext())
        word.text = people
        word.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.toFloat())
        word.gravity = Gravity.CENTER
        val drawable = getDrawable(R.drawable.text_view_people_badge)
        word.background = drawable

        word.setTextColor(getColor(android.R.color.white))
        word.setPadding(dpToPix(5f), dpToPix(5f), dpToPix(5f), dpToPix(5f))

        val lpRight = FlexboxLayout.LayoutParams(
            FlexboxLayout.LayoutParams.WRAP_CONTENT,
            FlexboxLayout.LayoutParams.WRAP_CONTENT
        )
        lpRight.setMargins(dpToPix(5f), dpToPix(5f), dpToPix(5f), dpToPix(5f))
        word.layoutParams = lpRight
        word.setOnLongClickListener {
            WordPopup(requireContext(), this).show(it, people)
            true
        }
        binding.flexBox.addView(word)
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