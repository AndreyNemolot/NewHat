package com.example.utilites

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment

fun Fragment.getDrawable(@DrawableRes resId: Int): Drawable? {
    return requireContext().getDrawableCompat(resId)
}

fun Fragment.getColor(@ColorRes colorId: Int): Int {
    return requireContext().getColorCompat(colorId)
}

fun Fragment.dpToPix(value: Float): Int {
    return requireContext().dpToPixSize(value)
}

fun Fragment.getDrawableCompat(resId: Int): Drawable? {
    return AppCompatResources.getDrawable(requireContext(), resId)
}