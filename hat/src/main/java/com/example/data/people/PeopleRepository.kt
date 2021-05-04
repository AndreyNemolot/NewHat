package com.example.data.people

import com.example.domain.model.Player
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class PeopleRepository @Inject constructor() {
    private val playerSet: MutableSet<Player> = mutableSetOf()

    private val state_: MutableStateFlow<List<Player>> = MutableStateFlow(emptyList())
    val state: Flow<List<Player>> = state_

    fun getPlayers(): List<Player> {
        return playerSet.map { Player(name = it.name) }.toList()
    }

    fun getPlayerByName(playerName: String): Player {
        val player = playerSet.findLast { it.name == playerName }
        return requireNotNull(player) { "Doesn't find $playerName player" }
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
