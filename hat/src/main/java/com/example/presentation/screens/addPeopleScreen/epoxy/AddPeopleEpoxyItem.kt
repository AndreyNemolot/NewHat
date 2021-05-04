package com.example.presentation.screens.addPeopleScreen.epoxy

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.example.presentation.R
import com.example.presentation.databinding.EpoxyAddPeopleItemBinding
import com.example.utilites.inflateSelf
import kotlinx.android.synthetic.main.epoxy_add_people_item.view.*
import java.util.zip.Inflater

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AddPeopleEpoxyItem: ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @set:CallbackProp
    var listener: EpoxyPeopleController.PlayerListener? = null
    val binding = EpoxyAddPeopleItemBinding.bind(inflateSelf(R.layout.epoxy_add_people_item))


    init {
        binding.addPlayer.setOnClickListener {

            listener?.addPlayer(binding.player.text.toString())
            binding.player.text.clear()
        }
    }






}