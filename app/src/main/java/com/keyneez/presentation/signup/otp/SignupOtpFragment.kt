package com.keyneez.presentation.signup.otp

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentSignupOtpBinding

class SignupOtpFragment : BindingFragment<FragmentSignupOtpBinding>(R.layout.fragment_signup_otp) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = SignupOtpFragment()
    }
}
