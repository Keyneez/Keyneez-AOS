package com.keyneez.util.binding

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lab.keyneez.R

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

    @JvmStatic
    @BindingAdapter("url")
    fun setImage(view: ImageView, url: MutableLiveData<String>) {
        url?.let {
        }
    }

    @JvmStatic
    @BindingAdapter("keywordColor")
    fun TextView.setKeywordColor(txt: String?) {
        txt?.let {
            when (txt) {
                "문화" -> {
                    this.setTextColor(this.context.getColor(R.color.mint500))
                    this.background = this.context.getDrawable(R.drawable.shape_mint500_line_rect)
                }
                "진로" -> {
                    this.setTextColor(this.context.getColor(R.color.green600))
                    this.background = this.context.getDrawable(R.drawable.shape_green600_line_rect)
                }
                "봉사" -> {
                    this.setTextColor(this.context.getColor(R.color.purple500))
                    this.background = this.context.getDrawable(R.drawable.shape_purple500_line_rect)
                }

                "여행" -> {
                    this.setTextColor(this.context.getColor(R.color.pink500))
                    this.background = this.context.getDrawable(R.drawable.shape_pink500_line_rect)
                }

                "진로" -> {
                    this.setTextColor(this.context.getColor(R.color.red500))
                    this.background = this.context.getDrawable(R.drawable.shape_red500_line_rect)
                }
            }
        }
    }
}
