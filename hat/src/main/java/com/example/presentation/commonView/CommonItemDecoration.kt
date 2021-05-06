package com.example.presentation.commonView

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.utilites.dpToPixSize

class CommonItemDecoration(private val horizontal: Float, private val vertical: Float) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.setEmpty()

        val position = parent.getChildAdapterPosition(view)
        if (position < 0) return

        val marginVertical = view.context.dpToPixSize(vertical)
        val marginHorizontal = view.context.dpToPixSize(horizontal)
        outRect.top = marginVertical
        outRect.left = marginHorizontal
        outRect.bottom = marginVertical
        outRect.right = marginHorizontal
    }
}