package com.example.presentation.screens.gameConfigScreen

sealed class Command {
    class AddPeopleCommand(val people: String) : Command()
}