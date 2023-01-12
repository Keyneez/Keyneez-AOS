package com.keyneez.util.extension // ktlint-disable filename

import android.content.Context

fun Context.dpToPx(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale).toInt()
}
