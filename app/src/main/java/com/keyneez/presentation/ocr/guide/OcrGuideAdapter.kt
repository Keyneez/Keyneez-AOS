package com.keyneez.presentation.ocr.guide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.keyneez.presentation.ocr.guide.complete.OcrCompleteFragment
import com.keyneez.presentation.ocr.guide.guide.OcrGuideFragment

class OcrGuideAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf<Fragment>(OcrGuideFragment(), OcrCompleteFragment())

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}
