package com.keyneez.presentation.login.otp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.keyneez.presentation.login.LoginActivity
import com.keyneez.presentation.main.MainActivity
import com.keyneez.presentation.signup.otp.SignupOtpViewModel
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.hideKeyboard
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentLoginOtpBinding

class LoginOtpFragment : BindingFragment<FragmentLoginOtpBinding>(R.layout.fragment_login_otp) {
    private val viewModel by viewModels<SignupOtpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        initBackBtnClickListener()
        setupPasswordText()
    }

    private fun initHideKeyboard() {
        binding.layoutLoginOtp.setOnClickListener { requireContext().hideKeyboard(requireView()) }
    }

    private fun initBackBtnClickListener() {
        binding.btnLoginOtpBack.setOnSingleClickListener {
            (activity as LoginActivity).intentToPreviousPage()
        }
    }

    private fun setupPasswordText() {
        viewModel.passwordText.observe(viewLifecycleOwner) { pwd ->
            if (pwd.length >= 6) {
                val toMain = Intent(activity, MainActivity::class.java)
                requireActivity().setResult(AppCompatActivity.RESULT_OK, toMain)
                startActivity(toMain)
                requireActivity().finish()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginOtpFragment()
    }
}
