package com.example.presentation.screens.mainScreen.epoxy

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.presentation.R
import com.example.utilites.inflateSelf
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.epoxy_main_item.view.*

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class MainMenuEpoxyItem: FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inflateSelf(R.layout.epoxy_main_item)
    }

    @TextProp
    fun setTitle(title_: CharSequence) {
        title.text = title_
    }
}