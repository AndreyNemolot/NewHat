package com.example.presentation.screens.addPeopleScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.people.PeopleRepository
import com.example.domain.model.Player
import kotlinx.coroutines.*
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
//    val commandFlow = MutableSharedFlow<Command>()


    init {
        peopleRepository.state.onEach {
            stateAddPeople = stateAddPeople.copy(playerList = it)
            stateFlow.value = stateAddPeople
        }.launchIn(viewModelScope)
    }

    fun addPlayer(player: String) {
        peopleRepository.addPeople(
            Player(name = player)
        )
    }

    fun removePeople(name: String) {
        peopleRepository.removePeople(
            Player(name)
        )
    }

    fun getPeople() {
         peopleRepository.getPeople()
    }

}