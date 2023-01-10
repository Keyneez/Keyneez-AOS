package com.keyneez.presentation.signup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.keyneez.presentation.signup.danal.complete.DanalCompleteFragment
import com.keyneez.presentation.signup.danal.guide.DanalGuideFragment
import com.keyneez.presentation.signup.interest.InterestFragment
import com.keyneez.presentation.signup.pin.SignupPinFragment
import com.keyneez.presentation.signup.pin.confirm.SignupPinConfirmFragment
import com.keyneez.presentation.signup.tendency.TendencyFragment
import com.keyneez.presentation.signup.test.complete.TestCompleteFragment
import com.keyneez.presentation.signup.test.guide.TestGuideFragment

class SignupAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf(
        DanalGuideFragment(),
        DanalCompleteFragment(),
        TestGuideFragment(),
        TendencyFragment(),
        InterestFragment(),
        TestCompleteFragment(),
        SignupPinFragment(),
        SignupPinConfirmFragment()
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}
