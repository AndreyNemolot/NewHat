package com.example.presentation.screens.commandsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.CommandInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CommandsScreenViewModel @Inject constructor(
    private val commandInteractor: CommandInteractor
) : ViewModel() {

    private var commandState: CommandScreenState = CommandScreenState()
    val stateFlow = MutableStateFlow(commandState)
//    val commandFlow = MutableSharedFlow<AddPlayerCommand>()


    init {
        commandInteractor.commandState.onEach {
            commandState = commandState.copy(commandList = it)
            stateFlow.emit(commandState)
        }.launchIn(viewModelScope)
    }




}