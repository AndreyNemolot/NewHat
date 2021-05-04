package com.example.presentation.screens.addPeopleScreen

import com.example.domain.model.Player


data class AddPeopleScreenState(
    var playerList: List<Player> = listOf(Player("asd"))
)
