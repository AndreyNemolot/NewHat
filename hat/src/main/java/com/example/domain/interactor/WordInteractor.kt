package com.example.domain.interactor

import com.example.data.people.PeopleRepository
import com.example.data.words.WordRepository
import com.example.domain.model.Player
import com.example.domain.model.Word
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class WordInteractor @Inject constructor(
    private val wordRepository: WordRepository
) {

    val state: Flow<List<Word>> = wordRepository.state

    fun getWord(): List<Word> {
        return wordRepository.getWord()
    }

    fun addWord(word: Word) {
        wordRepository.addWord(word)
    }

    fun removeWord(word: Word) {
        wordRepository.removeWord(word)
    }

    fun gerWordsSize(): Int {
        return wordRepository.getWordSize()
    }


}