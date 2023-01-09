package com.keyneez.presentation.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.keyneez.util.binding.BindingActivity
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityOnboardingBinding

class OnboardingActivity :
    BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initOnboardingViewPager()
    }

    private fun initOnboardingViewPager() {
        val viewPager = binding.vpOnboarding
        val dotIndicator = binding.tabOnboardingDot
        viewPager.adapter = OnboardingAdapter(this)
        dotIndicator.attachTo(viewPager)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                viewModel.setPosition(position)
            }
        })
    }
}
