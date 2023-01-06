package com.keyneez.presentation.signup.otp.confirm

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentSignupOtpConfirmBinding

class SignupOtpConfirmFragment :
    BindingFragment<FragmentSignupOtpConfirmBinding>(R.layout.fragment_signup_otp_confirm) {
    private val viewModel by viewModels<SignupOtpConfirmViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBackBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.btnSignupOtpConfirmBack.setOnSingleClickListener {
            viewModel.resetPassword()
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    companion object {
        fun newInstance() = SignupOtpConfirmFragment()
    }
}
