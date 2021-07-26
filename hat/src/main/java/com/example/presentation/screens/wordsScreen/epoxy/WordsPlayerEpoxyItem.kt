package com.example.presentation.screens.wordsScreen.epoxy

import android.content.Context
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.presentation.R
import com.example.presentation.databinding.EpoxyWordsPlayerItemBinding
import com.example.utilites.inflateSelf

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class WordsPlayerEpoxyItem(context: Context) : ConstraintLayout(context) {


//    @set:CallbackProp
//    var listener: EpoxyPeopleController.PlayerListener? = null
    private val binding = EpoxyWordsPlayerItemBinding.bind(inflateSelf(R.layout.epoxy_words_player_item))

    @TextProp
    fun setPlayer(name: CharSequence) {
        binding.player.text = name
    }

    @TextProp
    fun setRestriction(count: CharSequence) {
        binding.wordsCounterRestriction.text = count
    }

    init {
        binding.root.setOnClickListener {
//            listener?.addPlayer(binding.player.text.toString())
//            binding.player.text.clear()
        }

        binding.player.setOnEditorActionListener { v, actionId, event ->
            when(actionId) {
                EditorInfo.IME_ACTION_SEND -> {
//                    listener?.addPlayer(binding.player.text.toString())
//                    binding.player.text.clear()
                    true
                }
                else -> false
            }
        }
    }

}