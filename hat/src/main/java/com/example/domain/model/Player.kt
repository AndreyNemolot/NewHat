package com.example.domain.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class Player(
    val name: String
) {
    private var words: MutableSet<Word> = mutableSetOf()
    private val channel: MutableStateFlow<List<Word>> = MutableStateFlow(listOf())
    val wordState: Flow<List<Word>> = channel

    fun addWord(word: Word) {
        words.add(word)
        channel.tryEmit(words.toList())
    }

    fun removeWord(word: Word) {
        words.remove(word)
        channel.tryEmit(words.toList())
    }

    fun getWords(): List<Word> {
        return words.toList()
    }
}