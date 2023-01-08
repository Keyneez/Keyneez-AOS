package com.keyneez.presentation.signup.otp.confirm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.main.MainActivity
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.presentation.signup.otp.SignupOtpViewModel
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentSignupOtpConfirmBinding

class SignupOtpConfirmFragment :
    BindingFragment<FragmentSignupOtpConfirmBinding>(R.layout.fragment_signup_otp_confirm) {
    private val viewModel by viewModels<SignupOtpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        setupPasswordText()
    }

    private fun initBackBtnClickListener() {
        binding.btnSignupOtpConfirmBack.setOnSingleClickListener {
            viewModel.initPassword()
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    private fun setupPasswordText() {
        viewModel.passwordText.observe(viewLifecycleOwner) { pwd ->
            if (pwd.length == 6) {
                // 이전 비밀번호와 비교 로직 필요
                // 서버 통신 - 유저 생성 (비밀번호)
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

    companion object {
        fun newInstance() = SignupOtpConfirmFragment()
    }
}
