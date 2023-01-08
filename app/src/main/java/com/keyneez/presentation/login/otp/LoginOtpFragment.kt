package com.keyneez.presentation.login.otp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.main.MainActivity
import com.keyneez.presentation.signup.otp.SignupOtpViewModel
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.hideKeyboard
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentLoginOtpBinding

class LoginOtpFragment : BindingFragment<FragmentLoginOtpBinding>(R.layout.fragment_login_otp) {
    private val viewModel by viewModels<SignupOtpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        setupPasswordText()
    }

    private fun initHideKeyboard() {
        binding.layoutLoginOtp.setOnClickListener { requireContext().hideKeyboard(requireView()) }
    }

    private fun setupPasswordText() {
        viewModel.passwordText.observe(viewLifecycleOwner) { pwd ->
            if (pwd.length >= 6) {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginOtpFragment()
    }
}
