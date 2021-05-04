package com.example.presentation.screens.mainScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainScreenViewModel @Inject constructor(
) : ViewModel() {

    private var state: ScreenState = ScreenState()
    val stateFlow = MutableStateFlow(ScreenState())

    init {

        val menuList = listOf(
            MainMenuWidget.NewGameWidget("New game"),
            MainMenuWidget.SettingsWidget("Settings"),
            MainMenuWidget.RulesWidget("Rules")
        )
        state = state.copy(menuList = menuList)
        stateFlow.value = state
    }


}