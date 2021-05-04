package com.example.presentation.screens.addWordsScreen

import android.os.Bundle
import android.os.Parcelable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
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
        viewModel = obtainViewModel()
        launchWhenCreated {
            viewModel.stateFlow.collect {
                updateState(it)
            }
        }
        binding.addButton.setOnClickListener {
            addPeople()
            binding.textInputEditText.text?.clear()
        }
    }

    private fun addPeople() {
        val peopleName = binding.textInputEditText.text.toString()
        viewModel.addPeople(peopleName)
    }

    private fun updateState(stateAddWord: AddWordScreenState) {
        binding.flexBox.removeAllViews()
        stateAddWord.wordList.forEach {
            addPeopleToFlexBox(it.word)
        }
    }

    private fun addPeopleToFlexBox(people: String) {
        val peopleTextView = TextView(requireContext())
        peopleTextView.text = people
        peopleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.toFloat())
        peopleTextView.gravity = Gravity.CENTER
        val drawable = getDrawable(R.drawable.text_view_people_badge)
        peopleTextView.background = drawable

        peopleTextView.setTextColor(getColor(android.R.color.white))
        peopleTextView.setPadding(dpToPix(5f), dpToPix(5f), dpToPix(5f), dpToPix(5f))

        val lpRight = FlexboxLayout.LayoutParams(
            FlexboxLayout.LayoutParams.WRAP_CONTENT,
            FlexboxLayout.LayoutParams.WRAP_CONTENT
        )
        lpRight.setMargins(dpToPix(5f), dpToPix(5f), dpToPix(5f), dpToPix(5f))
        peopleTextView.layoutParams = lpRight
        peopleTextView.setOnLongClickListener {
            WordPopup(requireContext(), this).show(it, people)
            true
        }
        binding.flexBox.addView(peopleTextView)
    }

    override fun remove(people: String) {
        viewModel.removePeople(people)

    }

    override fun edit(people: String) {
        binding.textInputEditText.setText(people)
        viewModel.removePeople(people)
    }

    @Parcelize
    class Screen : SupportAppScreen(), Parcelable {
        override fun getFragment(): Fragment {
            return AddWordsScreenFragment()
        }
    }
}