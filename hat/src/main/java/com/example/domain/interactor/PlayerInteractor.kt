package com.example.domain.interactor

import com.example.data.people.PeopleRepository
import com.example.domain.model.Player
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PlayerInteractor @Inject constructor(
    private val peopleRepository: PeopleRepository
) {

    val state: Flow<List<Player>> = peopleRepository.state

    fun getPeople(): List<Player> {
        return peopleRepository.getPeople()
    }

    fun addPeople(playerName: Player) {
        peopleRepository.addPeople(playerName)
    }

    fun removePeople(playerName: Player) {
        peopleRepository.removePeople(playerName)
    }

    fun getPeopleSize(): Int {
        return peopleRepository.getPeopleSize()
    }


}