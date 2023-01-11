package com.keyneez.presentation.signup.interest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentInterestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InterestFragment : BindingFragment<FragmentInterestBinding>(R.layout.fragment_interest) {
    private val viewModel by viewModels<InterestViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        initNextBtnClickListener()
        setupPatchUserTypeState()
    }

    private fun initBackBtnClickListener() {
        binding.btnInterestBack.setOnSingleClickListener {
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    private fun initNextBtnClickListener() {
        binding.btnInterestNext.setOnSingleClickListener {
            val tendency = (activity as SignupActivity).getTendency()
            viewModel.patchUserTypeSignup(tendency)
        }
    }

    private fun setupPatchUserTypeState() {
        viewModel.patchUserTypeState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    (activity as SignupActivity).setUserResult(requireNotNull(viewModel.userData.value))
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
        fun newInstance() = InterestFragment()
    }
}
