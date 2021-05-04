package com.example.presentation.screens.mainScreen.epoxy

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyControllerAdapter
import com.example.utilites.dpToPixSize

class MainFragmentItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.setEmpty()
        val adapter = parent.adapter as? EpoxyControllerAdapter ?: return

        val position = parent.getChildAdapterPosition(view)
        if (position < 0) return

        val model = adapter.getModelAtPosition(position)

        val layoutManager = parent.layoutManager as? LinearLayoutManager ?: return

        when (model) {
            is MainMenuEpoxyItemModel_ -> {
                val margin = view.context.dpToPixSize(16f)
                outRect.top = margin
                outRect.left = margin
            }
        }
    }
}