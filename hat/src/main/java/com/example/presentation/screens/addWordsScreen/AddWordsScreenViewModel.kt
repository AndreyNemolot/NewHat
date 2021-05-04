package com.example.presentation.screens.addWordsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.PlayerInteractor
import com.example.domain.model.Player
import com.example.domain.model.Word
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AddWordsScreenViewModel @Inject constructor(
//    private val wordInteractor: WordInteractor,
    private val playerInteractor: PlayerInteractor
) : ViewModel() {

    private var stateAddWord: AddWordScreenState = AddWordScreenState()
    val stateFlow = MutableStateFlow(AddWordScreenState())
//    val commandFlow = MutableSharedFlow<Command>()

    lateinit var player: Player


    fun initialize(playerName: String) {
        player = playerInteractor.getPlayerByName(playerName)

        player.wordState.onEach {
            stateAddWord = stateAddWord.copy(wordList = it)
            stateFlow.value = stateAddWord
        }.launchIn(viewModelScope)
    }

    fun addPeople(word: String) {
        player.addWord(Word(word))
    }

    fun removePeople(word: String) {
        player.removeWord(Word(word))
    }


}