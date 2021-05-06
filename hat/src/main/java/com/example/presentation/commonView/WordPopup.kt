package com.example.presentation.commonView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.example.presentation.R
import com.example.presentation.databinding.WordPopupBinding
import com.example.utilites.dpToPixSize
import com.example.utilites.getDrawableCompat
import kotlinx.android.extensions.LayoutContainer

class WordPopup(context: Context, val listener: PopupListener?) : PopupWindow(context),
    LayoutContainer {

    override val containerView: View
        get() = contentView

    private val vb: WordPopupBinding

    init {
        @SuppressLint("InflateParams")
        contentView = LayoutInflater.from(context).inflate(R.layout.word_popup, null, false)
        vb = WordPopupBinding.bind(contentView)
        setBackgroundDrawable(context.getDrawableCompat(R.color.transparent))
        isTouchable = true
        isOutsideTouchable = true
        isFocusable = true
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    fun show(anchor: View, people: String) {
        val context = containerView.context
        val location = locateView(anchor)
        showAtLocation(
            anchor,
            Gravity.TOP or Gravity.START,
            location.left - context.dpToPixSize(-6f),
            location.bottom
        )

        vb.removeButton.setOnClickListener {
            listener?.remove(people)
            dismiss()
        }
        vb.editButton.setOnClickListener {
            listener?.edit(people)
            dismiss()
        }
    }

    private fun locateView(v: View): Rect {
        val locations = IntArray(2)
        v.getLocationOnScreen(locations)
        return Rect().apply {
            left = locations[0]
            top = locations[1]
            right = locations[0] + v.width
            bottom = locations[1] + v.height
        }
    }

    interface PopupListener {
        fun remove(people: String)
        fun edit(people: String)
    }
}