package com.example.presentation.commandsScreen

import com.example.domain.model.Command

data class CommandScreenState(
    var commandList: List<Command> = emptyList()
)
