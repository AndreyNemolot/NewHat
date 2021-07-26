package com.example.presentation.screens.wordsScreen.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.example.domain.model.Player
import com.example.presentation.screens.wordsScreen.WordsScreenState

class EpoxyWordsController(
    private val wordsListener: WordsPlayerListener
) :TypedEpoxyController<WordsScreenState>() {

    override fun buildModels(data: WordsScreenState) {
        wordsConfigurationEpoxyItem {
            id("ADD_PLAYER_INPUT")
            listener(wordsListener)
        }

        data.players.forEach { player ->
            wordsPlayerEpoxyItem {
                id("PLAYER_${player.name}")
                player(player.name)
                restriction(data.wordsRestriction.toString())
//                wordsCount(player.wordsSize())
//                wordsRestriction(data.wordsRestriction)
//                listener(wordsListener)
            }
        }
    }

    interface WordsPlayerListener {
        fun openWordsScreen(player: Player)
        fun changeWordsRestrition(count: Int)

    }

}