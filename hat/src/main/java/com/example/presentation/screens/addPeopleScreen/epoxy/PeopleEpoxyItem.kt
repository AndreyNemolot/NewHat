package com.example.presentation.screens.addPeopleScreen.epoxy

import android.content.Context
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.example.presentation.R
import com.example.presentation.databinding.EpoxyPeopleItemBinding
import com.example.utilites.inflateSelf

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PeopleEpoxyItem(context: Context) : FrameLayout(context) {


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