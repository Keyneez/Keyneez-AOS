package com.keyneez.util.binding

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("isSelected")
    fun View.isSelected(selected: Boolean) {
        this.isSelected = selected
    }

    @JvmStatic
    @BindingAdapter("selectedInterestSet", "interestHashTag")
    fun TextView.setInterestIndex(set: LiveData<LinkedHashSet<String>>, interest: String) {
        this.text = set.value?.indexOf(interest)?.plus(1).toString()
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
