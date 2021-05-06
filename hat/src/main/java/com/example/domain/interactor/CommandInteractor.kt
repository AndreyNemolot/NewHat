package com.example.domain.interactor

import com.example.data.people.PeopleRepository
import com.example.domain.model.Command
import com.example.domain.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class CommandInteractor @Inject constructor(
    peopleRepository: PeopleRepository
) {

    val commandState: MutableStateFlow<List<Command>> = MutableStateFlow(emptyList())

    init {
        peopleRepository.state.onEach { players ->
            if (players.size >= 4 && players.size % 2 == 0) {
                commandState.emit(mixCommand(players))
            } else {
                commandState.emit(emptyList())
            }
        }.launchIn(CoroutineScope(Dispatchers.Default))
    }

    private fun mixCommand(players: List<Player>): List<Command> {
        val mutableList = players.toMutableList()
        val commandList: MutableList<Command> = mutableListOf()
        while (mutableList.isNotEmpty()) {
            val player1 = Random.nextInt(mutableList.lastIndex).let { idx ->
                mutableList[idx].also { mutableList.removeAt(idx) }
            }
            val player2 = if (mutableList.lastIndex == 0) {
                0
            } else {
                Random.nextInt(mutableList.lastIndex)
            }.let { idx ->
                mutableList[idx].also { mutableList.removeAt(idx) }
            }

            commandList.add(
                Command(
                    name = UUID.randomUUID().toString(),
                    player1 = player1,
                    player2 = player2
                )
            )
        }
        return commandList
    }
}