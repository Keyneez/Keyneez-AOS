package com.keyneez.presentation.signup.otp

import android.os.Bundle
import android.view.View
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentSignupOtpBinding

class SignupOtpFragment : BindingFragment<FragmentSignupOtpBinding>(R.layout.fragment_signup_otp) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBackBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.btnSignupOtpBack.setOnSingleClickListener {
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    companion object {
        fun newInstance() = SignupOtpFragment()
    }
}
