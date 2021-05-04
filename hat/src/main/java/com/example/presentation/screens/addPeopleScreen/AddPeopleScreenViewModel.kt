package com.example.presentation.screens.addPeopleScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.people.PeopleRepository
import com.example.domain.model.Player
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AddPeopleScreenViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    private var stateAddPeople: AddPeopleScreenState = AddPeopleScreenState()
    val stateFlow = MutableStateFlow(AddPeopleScreenState())
    val commandFlow = MutableSharedFlow<AddPlayerCommand>()


    init {
        peopleRepository.state.onEach {
            stateAddPeople = stateAddPeople.copy(playerList = it)
            stateFlow.value = stateAddPeople
        }.launchIn(viewModelScope)
    }

    fun addPlayer(playerName: String) {
        if(playerName.isBlank()) {
            commandFlow.tryEmit(AddPlayerCommand.AddPlayerError)
        } else {
            peopleRepository.addPeople(
                Player(name = playerName)
            )
        }
    }

    fun removePeople(idx: Int) {
        val name = peopleRepository.getPlayers().getOrNull(idx)
        if (name != null) {
            peopleRepository.removePeople(name)
        } else {
            commandFlow.tryEmit(AddPlayerCommand.RemovePlayerError)
        }
    }

    fun getPeople() {
         peopleRepository.getPlayers()
    }

}