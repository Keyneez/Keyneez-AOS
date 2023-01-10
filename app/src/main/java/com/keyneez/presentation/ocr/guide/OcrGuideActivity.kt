package com.keyneez.presentation.ocr.guide

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.keyneez.util.binding.BindingActivity
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityOcrGuideBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OcrGuideActivity : BindingActivity<ActivityOcrGuideBinding>(R.layout.activity_ocr_guide) {
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOcrGuideViewPager()
    }

    private fun initOcrGuideViewPager() {
        viewPager = binding.vpOcrGuide
        binding.vpOcrGuide.adapter = OcrGuideAdapter(this)
        binding.vpOcrGuide.isUserInputEnabled = false // disable swipe
    }

    /** 이전 페이지의 프래그먼트로 ViewPager 전환 */
    fun intentToPreviousPage() {
        binding.vpOcrGuide.currentItem--
    }

    /** 다음 페이지의 프래그먼트로 ViewPager 전환 */
    fun intentToNextPage() {
        binding.vpOcrGuide.currentItem++
    }
}
