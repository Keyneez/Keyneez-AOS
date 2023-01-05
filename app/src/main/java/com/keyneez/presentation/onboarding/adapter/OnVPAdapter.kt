package com.keyneez.presentation.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.keyneez.presentation.onboarding.OnFirstFragment
import com.keyneez.presentation.onboarding.OnFourthFragment
import com.keyneez.presentation.onboarding.OnSecondFragment
import com.keyneez.presentation.onboarding.OnThirdFragment

class OnVPAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return OnFirstFragment()
            1 -> return OnSecondFragment()
            2 -> return OnThirdFragment()
            3 -> return OnFourthFragment()
        }
        return OnFirstFragment()
    }
}
