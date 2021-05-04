package com.example.presentation.screens.addPeopleScreen.epoxy

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.presentation.R
import com.example.presentation.databinding.EpoxyPeopleItemBinding
import com.example.utilites.inflateSelf

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PeopleEpoxyItem : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @set:CallbackProp
    var listener: EpoxyPeopleController.PlayerListener? = null

    private val binding = EpoxyPeopleItemBinding.bind(inflateSelf(R.layout.epoxy_people_item))

    @TextProp
    fun setPeople(name: CharSequence) {
        binding.people.text = name

        binding.root.setOnClickListener {
            listener?.addWord(name.toString())
        }
    }
}