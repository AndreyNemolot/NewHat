package com.example.presentation.screens.commandsScreen

import com.example.domain.model.Command

data class CommandScreenState(
    var commandList: List<Command> = emptyList()
)
