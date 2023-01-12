package com.keyneez.presentation.login.pin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.keyneez.presentation.login.LoginActivity
import com.keyneez.presentation.login.pin.LoginPinViewModel.Companion.INVALID_PWD_CODE
import com.keyneez.presentation.login.pin.LoginPinViewModel.Companion.UNAUTHORIZED_USER_CODE
import com.keyneez.presentation.main.MainActivity
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.hideKeyboard
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentLoginPinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginPinFragment : BindingFragment<FragmentLoginPinBinding>(R.layout.fragment_login_pin) {
    private val viewModel by viewModels<LoginPinViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        initBackBtnClickListener()
        setupPasswordText()
        setupPostUserLoginState()
    }

    private fun initHideKeyboard() {
        binding.layoutLoginPin.setOnClickListener { requireContext().hideKeyboard(requireView()) }
    }

    private fun initBackBtnClickListener() {
        binding.btnLoginPinBack.setOnSingleClickListener {
            viewModel.resetPassword()
            (activity as LoginActivity).intentToPreviousPage()
        }
    }

    private fun setupPasswordText() {
        viewModel.passwordText.observe(viewLifecycleOwner) { pwd ->
            if (pwd.length >= 6) {
                val phone = (activity as LoginActivity).viewModel.phoneNumber.value.toString()
                viewModel.postUserLogin(phone, pwd)
            }
        }
    }

    private fun setupPostUserLoginState() {
        viewModel.stateMessage.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    viewModel.resetPassword()
                    val toMain = Intent(activity, MainActivity::class.java)
                    requireActivity().setResult(AppCompatActivity.RESULT_OK, toMain)
                    startActivity(toMain)
                    requireActivity().finish()
                }
                is UiState.Failure -> {
                    when (it.code) {
                        UNAUTHORIZED_USER_CODE -> requireContext().showSnackbar(
                            binding.root,
                            getString(
                                R.string.login_pin_unauthorized_msg
                            )
                        )
                        INVALID_PWD_CODE -> requireContext().showSnackbar(
                            binding.root,
                            getString(R.string.signup_pin_confirm_invalid_pwd_msg)
                        )
                        else -> requireContext().showSnackbar(
                            binding.root,
                            getString(R.string.msg_error)
                        )
                    }
                }
                is UiState.Error -> {
                    requireContext().showSnackbar(binding.root, getString(R.string.msg_error))
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginPinFragment()
    }
}
