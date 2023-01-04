package com.keyneez.presentation.ocr.guide

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.keyneez.util.binding.BindingActivity
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityOcrGuideBinding

class OcrGuideActivity : BindingActivity<ActivityOcrGuideBinding>(R.layout.activity_ocr_guide) {
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOcrGuideViewPager()
    }

    private fun initOcrGuideViewPager() {
        viewPager = binding.vpOcrGuide
        binding.vpOcrGuide.adapter = OcrGuideAdapter(this)
        binding.vpOcrGuide.isUserInputEnabled = false
    }

    /** ViewPager 프래그먼트 인덱스 설정 */
    fun selectIndex(newIndex: Int) {
        binding.vpOcrGuide.currentItem = newIndex
    }
}
