package com.keyneez.util.binding

import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("isSelected")
    fun ImageButton.isSelected(selected: LiveData<Boolean>) {
        this.isSelected = requireNotNull(selected.value)
    }

    @JvmStatic
    @BindingAdapter("isSelected")
    fun TextView.isSelected(selected: Boolean) {
        this.isSelected = requireNotNull(selected)
    }

    @JvmStatic
    @BindingAdapter("layoutMarginBottom")
    fun TextView.setLayoutMarginBottom(dimen: Float) {
        (this.layoutParams as ViewGroup.MarginLayoutParams).let {
            it.bottomMargin = dimen.toInt()
            this.layoutParams = it
        }
    }

    @JvmStatic
    @BindingAdapter("layoutMarginTop")
    fun TextView.setLayoutMarginTop(dimen: Float) {
        (this.layoutParams as ViewGroup.MarginLayoutParams).let {
            it.topMargin = dimen.toInt()
            this.layoutParams = it
        }
    }
}
