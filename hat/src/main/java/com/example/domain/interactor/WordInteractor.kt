package com.example.domain.interactor

import com.example.data.people.PeopleRepository
import com.example.domain.model.Word
import javax.inject.Inject
import kotlin.random.Random

class WordInteractor @Inject constructor(
    private val playerRepository: PeopleRepository
) {

//    val guessedWord: MutableList<Word> = mutableListOf()
    val unGuessedWord: MutableList<Word> = mutableListOf()


    fun invalidate() {
        val playerWords = playerRepository.getPlayers().flatMap { player ->
            player.getWords()
        }
//        guessedWord.clear()
        unGuessedWord.clear()
        unGuessedWord.addAll(playerWords)
    }

    fun guessWord(word: Word) {
        unGuessedWord.remove(word)
    }

    fun nextWord(): Word {
        val randomIdx = Random.nextInt(0, unGuessedWord.size)
        return unGuessedWord[randomIdx]
    }

    fun hasWords(): Boolean {
        return unGuessedWord.isNotEmpty()
    }


}