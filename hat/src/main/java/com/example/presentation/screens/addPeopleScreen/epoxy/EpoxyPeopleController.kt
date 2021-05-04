package com.example.presentation.screens.addPeopleScreen.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.example.domain.model.Player
import com.example.presentation.screens.addPeopleScreen.AddPeopleScreenState

class EpoxyPeopleController(
    private val playerListener: PlayerListener
) :TypedEpoxyController<AddPeopleScreenState>() {

    override fun buildModels(data: AddPeopleScreenState) {
        addPeopleEpoxyItem {
            id("ADD_PLAYER_INPUT")
            listener(playerListener)

        }

        data.playerList.forEach { people ->
            peopleEpoxyItem {
                id("PEOPLE_${people.name}")
                people(people.name)
            }
        }
    }

    interface PlayerListener {
        fun addPlayer(name: String)
    }

}