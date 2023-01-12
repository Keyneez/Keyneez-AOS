package com.keyneez.util.binding

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import coil.load
import coil.transform.RoundedCornersTransformation
import com.lab.keyneez.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setRoundedImage")
    fun ImageView.setRoundedImage(url: String?) {
        this.load(url) {
            fallback(R.drawable.img_like_background)
            placeholder(R.drawable.img_like_background)
            transformations(RoundedCornersTransformation(14f))
        }
    }

    @JvmStatic
    @BindingAdapter("setCharacterImage")
    fun ImageView.setCharacterImage(url: String?) {
        this.load(url) {
            fallback(R.drawable.img_id_main_character)
            placeholder(R.drawable.img_id_main_character)
        }
    }

    @JvmStatic
    @BindingAdapter("setOcrImage")
    fun ImageView.setOcrImage(url: String) {
        this.load(url)
    }

    @JvmStatic
    @BindingAdapter("isSelected")
    fun View.isSelected(selected: Boolean) {
        this.isSelected = selected
    }

    @JvmStatic
    @BindingAdapter("putStartDate", "putEndDate")
    fun TextView.setDuration(start: String, end: String) {
        if (start == null || end == null) {
            this.text = "2023"
            return
        }

        var tempStart = ""
        var tempEnd = ""
        if (start != null) tempStart = "${end.substring(4, 6)}.${end.substring(6, 8)}"
        if (end != null) tempEnd = "${end.substring(4, 6)}.${end.substring(6, 8)}"

        this.text = "$tempStart - $tempEnd"
    }

    @JvmStatic
    @BindingAdapter("putBirth")
    fun TextView.putBirth(birth: String?) {
        if (birth == null) {
            this.text = ""
            return
        }
        var tempBirth = ""
        tempBirth = "${birth.substring(0, 4)}.${birth.substring(4, 6)}.${birth.substring(6, 8)}"
        this.text = "$tempBirth"
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
