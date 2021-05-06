package com.example.domain.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class Player(
    val name: String
): WordStore {
    private var words: MutableSet<Word> = mutableSetOf()
    private val channel: MutableStateFlow<List<Word>> = MutableStateFlow(listOf())
    override val wordState: Flow<List<Word>> = channel

    override fun addWord(word: Word) {
        words.add(word)
        channel.tryEmit(words.toList())
    }

    override fun removeWord(word: Word) {
        words.remove(word)
        channel.tryEmit(words.toList())
    }

    override fun getWords(): List<Word> {
        return words.toList()
    }
}