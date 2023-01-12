package com.keyneez.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import com.keyneez.data.model.response.ResponsePatchUserTypeDto
import com.keyneez.util.binding.BindingActivity
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignupViewPager()
    }

    private fun initSignupViewPager() {
        binding.vpSignup.adapter = SignupAdapter(this)
        binding.vpSignup.isUserInputEnabled = false // disable swipe
    }

    /** 이전 페이지의 프래그먼트로 ViewPager 전환 */
    fun intentToPreviousPage() {
        binding.vpSignup.currentItem--
    }

    /** 다음 페이지의 프래그먼트로 ViewPager 전환 */
    fun intentToNextPage() {
        binding.vpSignup.currentItem++
    }

    /** 유저 이름 저장 */
    fun setUserName(name: String) {
        viewModel.setUserName(name)
    }

    /** 선택한 성향 저장 */
    fun setTendency(tendency: String) {
        viewModel.setTendency(tendency)
    }

    /** 테스트 결과 저장 */
    fun setUserResult(responsePatchUserTypeDto: ResponsePatchUserTypeDto) {
        viewModel.setTestResult(responsePatchUserTypeDto)
    }
}
