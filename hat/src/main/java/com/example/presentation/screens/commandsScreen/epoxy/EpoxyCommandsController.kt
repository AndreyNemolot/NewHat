package com.example.presentation.screens.commandsScreen.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.example.presentation.screens.commandsScreen.CommandScreenState

class EpoxyCommandsController(
) :TypedEpoxyController<CommandScreenState>() {

    override fun buildModels(data: CommandScreenState) {

        data.commandList.forEach { command ->
            commandEpoxyItem {
                id("COMMAND_${command.name}")
                command(command.name)
                player1(command.player1.name)
                player2(command.player2.name)
            }
        }
    }

}