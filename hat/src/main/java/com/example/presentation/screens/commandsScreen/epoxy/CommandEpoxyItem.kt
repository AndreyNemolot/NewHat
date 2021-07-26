package com.example.presentation.screens.commandsScreen.epoxy

import android.content.Context
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.presentation.R
import com.example.presentation.databinding.EpoxyCommandItemBinding
import com.example.utilites.inflateSelf
import com.google.android.material.card.MaterialCardView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CommandEpoxyItem(context: Context) : MaterialCardView(context) {

    private val binding = EpoxyCommandItemBinding.bind(inflateSelf(R.layout.epoxy_command_item))

    @TextProp
    fun setCommand(commandName: CharSequence) {
        binding.commnadName.text = commandName
    }

    @TextProp
    fun setPlayer1(playerName: CharSequence) {
        binding.player1.text = playerName
    }

    @TextProp
    fun setPlayer2(playerName: CharSequence) {
        binding.player2.text = playerName
    }
}