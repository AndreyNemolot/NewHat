package com.example.presentation.screens.addWordsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.WordInteractor
import com.example.domain.model.Word
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AddWordsScreenViewModel @Inject constructor(
    private val wordInteractor: WordInteractor
) : ViewModel() {

    private var stateAddWord: AddWordScreenState = AddWordScreenState()
    val stateFlow = MutableStateFlow(AddWordScreenState())
//    val commandFlow = MutableSharedFlow<Command>()


    init {
        wordInteractor.state.onEach {
            stateAddWord = stateAddWord.copy(wordList = it)
            stateFlow.value = stateAddWord
        }.launchIn(viewModelScope)
    }

    fun addPeople(word: String) {
        wordInteractor.addWord(
            Word(word)
        )
    }

    fun removePeople(word: String) {
        wordInteractor.removeWord(
            Word(word)
        )
    }

    fun getPeople() {
        wordInteractor.getWord()
    }

}