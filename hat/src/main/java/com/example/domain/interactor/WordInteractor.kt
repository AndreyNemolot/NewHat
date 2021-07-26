package com.example.domain.interactor

import com.example.data.people.PeopleRepository
import com.example.domain.model.Word
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

class WordInteractor @Inject constructor(
    private val playerRepository: PeopleRepository
) {

//    val guessedWord: MutableList<Word> = mutableListOf()
    val unguessedWord: MutableList<Word> = mutableListOf()
    var wordsRestriction: Int = 0
    set(value) {
        field = value
        wordsRestrictionObserver_.tryEmit(value)
    }

    private val wordsRestrictionObserver_: MutableStateFlow<Int> = MutableStateFlow(wordsRestriction)
    val wordsRestrictionObserver: Flow<Int> = wordsRestrictionObserver_


    fun invalidate() {
        val playerWords = playerRepository.getPlayers().flatMap { player ->
            player.getWords()
        }
//        guessedWord.clear()
        unguessedWord.clear()
        unguessedWord.addAll(playerWords)
    }

    fun guessWord(word: Word) {
        unguessedWord.remove(word)
    }

    fun nextWord(): Word {
        val randomIdx = Random.nextInt(0, unguessedWord.size)
        return unguessedWord[randomIdx]
    }

    fun hasWords(): Boolean {
        return unguessedWord.isNotEmpty()
    }


}