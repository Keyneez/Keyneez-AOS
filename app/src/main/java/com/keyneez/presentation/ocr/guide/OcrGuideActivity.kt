package com.keyneez.presentation.ocr.guide

import android.Manifest
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.showToast
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
        binding.vpOcrGuide.isUserInputEnabled = false // disable swipe
    }

    /** 해당 인덱스의 프래그먼트로 ViewPager 전환 */
    fun selectIndex(newIndex: Int) {
        binding.vpOcrGuide.currentItem = newIndex
    }
}
