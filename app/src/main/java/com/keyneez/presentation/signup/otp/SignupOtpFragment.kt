package com.keyneez.presentation.signup.otp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentSignupOtpBinding

class SignupOtpFragment : BindingFragment<FragmentSignupOtpBinding>(R.layout.fragment_signup_otp) {
    private val viewModel by viewModels<SignupOtpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        setupPasswordText()
    }

    private fun initBackBtnClickListener() {
        binding.btnSignupOtpBack.setOnSingleClickListener {
            viewModel.rearrangeKeypad()
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    private fun setupPasswordText() {
        viewModel.passwordText.observe(viewLifecycleOwner) { pwd ->
            if (pwd.length == 6) {
                // 비밀번호 저장 로직 필요
                viewModel.rearrangeKeypad()
                (activity as SignupActivity).intentToNextPage()
            }
        }
    }

    companion object {
        fun newInstance() = SignupOtpFragment()
    }
}
