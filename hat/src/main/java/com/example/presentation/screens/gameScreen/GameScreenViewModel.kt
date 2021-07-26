package com.example.presentation.screens.gameScreen

import androidx.lifecycle.ViewModel
import com.example.data.people.PeopleRepository
import com.example.presentation.screens.addWordsScreen.AddWordScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GameScreenViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    private var stateAddWord: AddWordScreenState = AddWordScreenState()
    val stateFlow = MutableStateFlow(AddWordScreenState())
//    val commandFlow = MutableSharedFlow<Command>()


    init {
    }



}