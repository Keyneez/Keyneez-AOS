package com.keyneez.presentation.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.keyneez.presentation.login.otp.LoginOtpFragment
import com.keyneez.presentation.login.phone.PhoneFragment

class LoginAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf(
        PhoneFragment(),
        LoginOtpFragment()
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}
