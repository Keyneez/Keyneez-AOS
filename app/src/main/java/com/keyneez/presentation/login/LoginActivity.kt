package com.keyneez.presentation.login

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.keyneez.util.binding.BindingActivity
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityLoginBinding

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLoginViewPager()
    }

    private fun initLoginViewPager() {
        viewPager = binding.vpLogin
        binding.vpLogin.adapter = LoginAdapter(this)
        binding.vpLogin.isUserInputEnabled = false // disable swipe
    }

    /** 이전 페이지의 프래그먼트로 ViewPager 전환 */
    fun intentToPreviousPage() {
        binding.vpLogin.currentItem--
    }

    /** 다음 페이지의 프래그먼트로 ViewPager 전환 */
    fun intentToNextPage() {
        binding.vpLogin.currentItem++
    }
}
