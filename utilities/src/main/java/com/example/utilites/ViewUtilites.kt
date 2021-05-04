package com.example.utilites

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import kotlin.math.ceil

inline var TextView.textOrGone: CharSequence?
    inline get() = text
    inline set(value) {
        text = value
        isGone = value.isNullOrEmpty()
    }

fun ViewGroup.inflateSelf(res: Int): View {
    return inflate(context, res, this)
}

fun Context.dpToPixSize(value: Float): Int {
    return ceil(value * resources.displayMetrics.density).toInt()
}

fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable? {
    return AppCompatResources.getDrawable(this, resId)
}

fun Context.getColorCompat(@ColorRes resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}