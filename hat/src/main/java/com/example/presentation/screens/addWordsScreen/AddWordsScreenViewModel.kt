package com.example.presentation.screens.addWordsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.PlayerInteractor
import com.example.domain.model.Word
import com.example.domain.model.WordStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AddWordsScreenViewModel @Inject constructor(
    private val playerInteractor: PlayerInteractor
) : ViewModel() {

    private var stateAddWord: AddWordScreenState = AddWordScreenState()
    val stateFlow = MutableStateFlow(AddWordScreenState())
//    val commandFlow = MutableSharedFlow<Command>()

    lateinit var wordStore: WordStore


    fun initialize(playerName: String) {
        wordStore = playerInteractor.getPlayerByName(playerName)

        wordStore.wordState.onEach {
            stateAddWord = stateAddWord.copy(wordList = it)
            stateFlow.value = stateAddWord
        }.launchIn(viewModelScope)
    }

    fun addPeople(word: String) {
        wordStore.addWord(Word(word))
    }

    fun removePeople(word: String) {
        wordStore.removeWord(Word(word))
    }


}