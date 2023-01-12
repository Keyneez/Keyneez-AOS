package com.keyneez.presentation.signup.pin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentSignupPinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupPinFragment : BindingFragment<FragmentSignupPinBinding>(R.layout.fragment_signup_pin) {
    private val viewModel by viewModels<SignupPinViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        setupPasswordText()
        setupPatchPwdSignup()
    }

    private fun initBackBtnClickListener() {
        binding.btnSignupPinBack.setOnSingleClickListener {
            viewModel.rearrangeKeypad()
            viewModel.resetPassword()
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    private fun setupPasswordText() {
        viewModel.passwordText.observe(viewLifecycleOwner) { pwd ->
            if (pwd.length == 6) {
                viewModel.patchPwdSignup()
            }
        }
    }

    private fun setupPatchPwdSignup() {
        viewModel.stateMessage.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    viewModel.resetPassword()
                    (activity as SignupActivity).intentToNextPage()
                }
                is UiState.Failure -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_error)
                )
                is UiState.Error -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_error)
                )
            }
        }
    }

    companion object {
        fun newInstance() = SignupPinFragment()
    }
}
