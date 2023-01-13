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
    @BindingAdapter("setImage")
    fun ImageView.setImage(url: String?) {
        this.load(url)
    }

    @JvmStatic
    @BindingAdapter("isSelected")
    fun View.isSelected(selected: Boolean) {
        this.isSelected = selected
    }

    @JvmStatic
    @BindingAdapter("putStartDate", "putEndDate")
    fun TextView.setDuration(start: String?, end: String?) {
        if (start == null || end == null) {
            this.text = "2023 -"
            return
        }

        val tempStart = "${end.substring(4, 6)}.${end.substring(6, 8)}"
        val tempEnd = "${end.substring(4, 6)}.${end.substring(6, 8)}"

        this.text = "$tempStart - $tempEnd"
    }

    @JvmStatic
    @BindingAdapter("putBirth")
    fun TextView.putBirth(birth: String?) {
        if (birth == null || birth.length < 7) {
            this.text = ""
            return
        }

        val tempBirth = "${birth.substring(0, 4)}.${birth.substring(4, 6)}.${birth.substring(6, 8)}"
        this.text = tempBirth
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

                "경제" -> {
                    this.setTextColor(this.context.getColor(R.color.red500))
                    this.background = this.context.getDrawable(R.drawable.shape_red500_line_rect)
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("replaceNewline")
    fun TextView.replaceNewline(string: String?) {
        this.text = string?.replace("\n", " ")
    }

    @JvmStatic
    @BindingAdapter("card")
    fun ImageView.card(string: String?) {
        when (string) {
            "문화" -> this.setImageDrawable(R.drawable.ic_home_card_mint)
            "진로" -> this.setImageDrawable(R.drawable.ic_home_card_green)
            "봉사" -> this.setImageDrawable(R.drawable.ic_home_card_purple)
            "여행" -> this.setImageDrawable(R.drawable.ic_home_card_pink)
            "경제" -> this.setImageDrawable(R.drawable.ic_home_card_red)
        }
    }
}

private fun ImageView.setImageDrawable(drawable: Int) {
}
