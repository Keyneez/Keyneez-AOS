package com.keyneez.presentation.login.pin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.keyneez.presentation.login.LoginActivity
import com.keyneez.presentation.main.MainActivity
import com.keyneez.presentation.signup.pin.SignupPinViewModel
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.hideKeyboard
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentLoginPinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginPinFragment : BindingFragment<FragmentLoginPinBinding>(R.layout.fragment_login_pin) {
    private val viewModel by viewModels<SignupPinViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        initBackBtnClickListener()
        setupPasswordText()
    }

    private fun initHideKeyboard() {
        binding.layoutLoginPin.setOnClickListener { requireContext().hideKeyboard(requireView()) }
    }

    private fun initBackBtnClickListener() {
        binding.btnLoginPinBack.setOnSingleClickListener {
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
        fun newInstance() = LoginPinFragment()
    }
}
