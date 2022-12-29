package com.keyneez.util.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/** hide keyboard from window */
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

/** Make a Snackbar to display a message for 2 seconds */
fun Context.showSnackbar(view: View, msg: String) {
    Snackbar.make(view, msg, Toast.LENGTH_SHORT).show()
}

/** Make a Toast to display a message for 2 seconds */
fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
