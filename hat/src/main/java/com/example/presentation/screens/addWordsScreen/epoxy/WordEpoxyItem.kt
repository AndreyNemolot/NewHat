package com.example.presentation.screens.addWordsScreen.epoxy

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.example.domain.model.Word
import com.example.presentation.R
import com.example.presentation.commonView.WordPopup
import com.example.presentation.databinding.EpoxyWordItemBinding
import com.example.utilites.inflateSelf
import com.google.android.flexbox.FlexboxLayoutManager

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class WordEpoxyItem(context: Context) : LinearLayout(context) {


    @set:CallbackProp
    var listener: WordPopup.PopupListener? = null

    private val binding = EpoxyWordItemBinding.bind(inflateSelf(R.layout.epoxy_word_item))

    init {
        val drawable = getDrawable(context, R.drawable.text_view_people_badge)
        background = drawable
        layoutParams = FlexboxLayoutManager.LayoutParams(
            FlexboxLayoutManager.LayoutParams.WRAP_CONTENT,
            FlexboxLayoutManager.LayoutParams.WRAP_CONTENT
        )

    }

    val floatProperty = object : FloatPropertyCompat<View>("") {

        override fun setValue(view: View, property: Float) {
            view.scaleX = property
            view.scaleY = property
        }

        override fun getValue(view: View): Float {
            return view.scaleX
        }
    }


    @ModelProp
    fun setPeople(word: Word) {
        binding.word.text = word.word

        binding.root.setOnLongClickListener {
            SpringAnimation(binding.root, floatProperty, 1f).apply {
                spring.setStiffness(SpringForce.STIFFNESS_LOW).dampingRatio = 0.25f
                minimumVisibleChange = DynamicAnimation.MIN_VISIBLE_CHANGE_SCALE
                setStartVelocity(6f)
                start()
            }

            WordPopup(context, listener).show(this, word.word)
            true
        }
    }
}