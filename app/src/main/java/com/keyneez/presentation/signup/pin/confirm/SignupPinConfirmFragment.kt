package com.keyneez.presentation.signup.pin.confirm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.keyneez.presentation.main.MainActivity
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentSignupPinConfirmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupPinConfirmFragment :
    BindingFragment<FragmentSignupPinConfirmBinding>(R.layout.fragment_signup_pin_confirm) {
    private val viewModel by viewModels<SignupPinConfirmViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        setupPasswordText()
        setupPostPwdCheckState()
    }

    private fun initBackBtnClickListener() {
        binding.btnSignupPinConfirmBack.setOnSingleClickListener {
            viewModel.rearrangeKeypad()
            viewModel.resetPassword()
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    private fun setupPasswordText() {
        viewModel.passwordText.observe(viewLifecycleOwner) { pwd ->
            if (pwd.length == 6) {
                viewModel.postPwdCheck(pwd)
            }
        }
    }

    private fun setupPostPwdCheckState() {
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
                    requireContext().showSnackbar(binding.root, getString(R.string.signup_pin_confirm_invalid_pwd_msg))
                    viewModel.resetPassword()
                }
                is UiState.Error -> requireContext().showSnackbar(binding.root, getString(R.string.msg_error))
            }
        }
    }

    companion object {
        fun newInstance() = SignupPinConfirmFragment()
    }
}
