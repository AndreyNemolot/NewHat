package com.example.presentation.screens.gameConfigScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.people.PeopleRepository
import com.example.presentation.screens.addWordsScreen.AddWordScreenState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GameConfigScreenViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    private var stateAddWord: AddWordScreenState = AddWordScreenState()
    val stateFlow = MutableStateFlow(AddWordScreenState())
//    val commandFlow = MutableSharedFlow<Command>()


    init {
//        peopleRepository.state.onEach {
//            stateAddWord = stateAddWord.copy(wordList = it)
//            stateFlow.value = stateAddWord
//        }.launchIn(viewModelScope)
    }



}