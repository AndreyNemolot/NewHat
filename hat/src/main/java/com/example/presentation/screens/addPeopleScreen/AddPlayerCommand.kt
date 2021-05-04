package com.example.presentation.screens.addPeopleScreen

sealed class AddPlayerCommand {
    object AddPlayerError: AddPlayerCommand()
    object RemovePlayerError: AddPlayerCommand()
}
