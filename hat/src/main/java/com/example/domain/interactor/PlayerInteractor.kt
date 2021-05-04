package com.example.domain.interactor

import com.example.data.people.PeopleRepository
import com.example.domain.model.Player
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayerInteractor @Inject constructor(
    private val peopleRepository: PeopleRepository
) {

    val state: Flow<List<Player>> = peopleRepository.state

    fun getPlayer(): List<Player> {
        return peopleRepository.getPlayers()
    }

    fun addPlayer(playerName: Player) {
        peopleRepository.addPeople(playerName)
    }

    fun removePlayer(playerName: Player) {
        peopleRepository.removePeople(playerName)
    }

    fun getPlayerSize(): Int {
        return peopleRepository.getPeopleSize()
    }

    fun getPlayerByName(playerName: String): Player {
        return peopleRepository.getPlayerByName(playerName)
    }

}