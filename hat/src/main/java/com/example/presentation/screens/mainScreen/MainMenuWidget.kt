package com.example.presentation.screens.mainScreen


sealed class MainMenuWidget(
    val title: String
) {
    class NewGameWidget(title: String) : MainMenuWidget(title)
    class SettingsWidget(title: String) : MainMenuWidget(title)
    class RulesWidget(title: String) : MainMenuWidget(title)

}