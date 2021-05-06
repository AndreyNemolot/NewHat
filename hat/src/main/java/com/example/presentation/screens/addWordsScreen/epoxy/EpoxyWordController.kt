package com.example.presentation.screens.addWordsScreen.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.example.domain.model.Word
import com.example.presentation.commonView.WordPopup

class EpoxyWordController(
    private val wordListener: WordPopup.PopupListener
) :TypedEpoxyController<List<Word>>() {

    override fun buildModels(data: List<Word>) {

        data.forEach { word ->
            wordEpoxyItem {
                id("WORD_${word.word}")
                people(word)
                listener(wordListener)
            }
        }
    }

    interface WordListener {
        fun click(word: Word)
    }

}