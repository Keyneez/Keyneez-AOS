package com.keyneez.presentation.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.keyneez.presentation.onboarding.OnFirstFragment
import com.keyneez.presentation.onboarding.OnFourthFragment
import com.keyneez.presentation.onboarding.OnSecondFragment
import com.keyneez.presentation.onboarding.OnThirdFragment

class OnboardingAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf<Fragment>(
        OnFirstFragment(),
        OnSecondFragment(),
        OnThirdFragment(),
        OnFourthFragment()
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}
