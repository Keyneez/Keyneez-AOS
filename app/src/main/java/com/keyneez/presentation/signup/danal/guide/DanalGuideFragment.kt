package com.keyneez.presentation.signup.danal.guide

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.presentation.signup.danal.guide.DanalGuideViewModel.Companion.EXISTENTIAL_USER_CODE
import com.keyneez.presentation.signup.danal.guide.DanalGuideViewModel.Companion.USER_DATA_NULL_CODE
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentDanalGuideBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DanalGuideFragment :
    BindingFragment<FragmentDanalGuideBinding>(R.layout.fragment_danal_guide) {
    private val viewModel by viewModels<DanalGuideViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        setupPostDanalSignup()
    }

    private fun initBackBtnClickListener() {
        binding.btnDanalGuideBack.setOnSingleClickListener { requireActivity().finish() }
    }

    private fun setupPostDanalSignup() {
        viewModel.postDanalSignupState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    (activity as SignupActivity).setUserName(viewModel.userName.value.toString())
                    (activity as SignupActivity).intentToNextPage()
                }
                is UiState.Failure -> {
                    when (it.code) {
                        EXISTENTIAL_USER_CODE -> {
                            requireContext().showSnackbar(
                                binding.root,
                                getString(R.string.danal_guide_msg_existential_user)
                            )
                        }
                        USER_DATA_NULL_CODE -> {
                            requireContext().showSnackbar(
                                binding.root,
                                getString(R.string.msg_error)
                            )
                        }
                    }
                }
                is UiState.Error -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_error)
                )
            }
        }
    }

    companion object {
        fun newInstance() = DanalGuideFragment()
    }
}
