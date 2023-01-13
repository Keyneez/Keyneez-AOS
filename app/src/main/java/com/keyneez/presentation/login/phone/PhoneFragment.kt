package com.keyneez.presentation.login.phone

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.login.LoginActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.hideKeyboard
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentPhoneBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneFragment : BindingFragment<FragmentPhoneBinding>(R.layout.fragment_phone) {
    private val viewModel by viewModels<PhoneViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initHideKeyboard()
        initBackBtnClickListener()
        initNextBtnClickListener()
    }

    private fun initHideKeyboard() {
        binding.layoutPhone.setOnClickListener { requireContext().hideKeyboard(requireView()) }
    }

    private fun initBackBtnClickListener() {
        binding.btnPhoneBack.setOnSingleClickListener { requireActivity().finish() }
    }

    private fun initNextBtnClickListener() {
        binding.btnPhoneNext.setOnSingleClickListener {
            requireContext().hideKeyboard(requireView())
            (activity as LoginActivity).setPhoneNumber(viewModel.phoneNumber.value.toString())
            (activity as LoginActivity).intentToNextPage()
        }
    }

    companion object {
        fun newInstance() = PhoneFragment()
    }
}
