package com.example.domain.model

import kotlinx.coroutines.flow.Flow

interface WordStore {

    val wordState: Flow<List<Word>>

    fun addWord(word: Word)

    fun removeWord(word: Word)

    fun getWords(): List<Word>

    fun wordsSize(): Int
}