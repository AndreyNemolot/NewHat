package com.example.presentation.screens.wordsScreen

import com.example.domain.model.Player

data class WordsScreenState(
    val players: List<Player> = emptyList(),
    val wordsRestriction: Int = 0
)