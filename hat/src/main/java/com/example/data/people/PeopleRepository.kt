package com.example.data.people

import com.example.domain.model.Player
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class PeopleRepository @Inject constructor() {
    private val playerSet: MutableSet<Player> = mutableSetOf()

    private val state_: MutableStateFlow<List<Player>> = MutableStateFlow(emptyList())
    val state: Flow<List<Player>> = state_

    init {
        println("ASDASD 1")
    }

    fun getPeople(): List<Player> {
        return playerSet.map { Player(name = it.name) }.toList()
    }

    fun addPeople(playerName: Player) {
        playerSet.add(playerName)
        state_.tryEmit(playerSet.toList())
    }

    fun removePeople(player: Player) {
        playerSet.remove(player)
        state_.tryEmit(playerSet.toList())
    }

    fun getPeopleSize(): Int {
        return playerSet.size
    }

}
