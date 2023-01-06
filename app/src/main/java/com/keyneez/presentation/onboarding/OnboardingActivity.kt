package com.keyneez.presentation.onboarding

import android.os.Bundle
import android.view.View
import com.keyneez.presentation.onboarding.adapter.OnboardingAdapter
import com.keyneez.util.binding.BindingActivity
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityOnboardingBinding

class OnboardingActivity :
    BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOnboardingViewPager()
    }

    private fun initOnboardingViewPager() {
        val viewPager = binding.vpOnboarding
        val dotIndicator = binding.tabOnboardingDot
        viewPager.adapter = OnboardingAdapter(this)
        dotIndicator.attachTo(viewPager)

//        if (viewPager.currentItem == 3) {
//            // 로그인, 회원가입 버튼 활성화
//            binding.btnOnboardingLogin.setVisibility(View.VISIBLE)
//            binding.btnOnboardingSignup.setVisibility(View.VISIBLE)
//        }
    }
}
