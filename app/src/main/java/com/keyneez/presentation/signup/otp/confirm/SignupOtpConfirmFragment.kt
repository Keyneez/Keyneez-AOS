package com.keyneez.presentation.signup.otp.confirm

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentSignupOtpConfirmBinding

class SignupOtpConfirmFragment : BindingFragment<FragmentSignupOtpConfirmBinding>(R.layout.fragment_signup_otp_confirm) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = SignupOtpConfirmFragment()
    }
}
