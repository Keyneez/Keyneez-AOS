package com.keyneez.util.extension

import android.app.Activity
import android.view.View

/** hide keyboard from activity window */
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}
