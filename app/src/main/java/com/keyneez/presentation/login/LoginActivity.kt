package com.keyneez.presentation.login

import android.os.Bundle
import androidx.activity.viewModels
import com.keyneez.util.binding.BindingActivity
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLoginViewPager()
    }

    private fun initLoginViewPager() {
        binding.vpLogin.adapter = LoginAdapter(this)
        binding.vpLogin.isUserInputEnabled = false // disable swipe
    }

    /** 전화번호 저장 */
    fun setPhoneNumber(number: String) {
        viewModel.setPhoneNumber(number)
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
