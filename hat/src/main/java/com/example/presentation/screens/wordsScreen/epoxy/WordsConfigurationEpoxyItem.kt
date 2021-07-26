package com.example.presentation.screens.wordsScreen.epoxy

import android.content.Context
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.example.presentation.R
import com.example.presentation.databinding.EpoxyWordsConfigurationItemBinding
import com.example.utilites.inflateSelf

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class WordsConfigurationEpoxyItem(context: Context) : ConstraintLayout(context) {


    @set:CallbackProp
    var listener: EpoxyWordsController.WordsPlayerListener? = null
    private val binding = EpoxyWordsConfigurationItemBinding.bind(inflateSelf(R.layout.epoxy_words_configuration_item))


    init {


        binding.wordsCount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.wordsCountText.text = seekBar?.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.progress?.let {
                    listener?.changeWordsRestrition(it)
                }
            }

        })
    }

    @AfterPropsSet
    fun afterPropSet() {
        binding.wordsCountText.text = binding.wordsCount.progress.toString()
    }

}