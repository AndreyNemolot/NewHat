package com.example.presentation.screens.mainScreen.epoxy

import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import com.airbnb.epoxy.TypedEpoxyController
import com.example.presentation.screens.mainScreen.MainMenuWidget

class EpoxyMainController(
    private val clickListener: MainMenu
) :TypedEpoxyController<List<MainMenuWidget>>() {

    override fun buildModels(data: List<MainMenuWidget>) {
        if (data.isEmpty()) return
        data.forEach { widget ->
            mainMenuEpoxyItem {
                id(widget.title)
                title(widget.title)
                onBind { _, view, _ ->
                    when (widget) {
                        is MainMenuWidget.NewGameWidget -> {
                            view.setOnClickListener {
                                clickListener.openNewGame()
                            }
                        }
                    }
                }
            }
        }
    }

    interface MainMenu {
        fun openNewGame()
    }

}