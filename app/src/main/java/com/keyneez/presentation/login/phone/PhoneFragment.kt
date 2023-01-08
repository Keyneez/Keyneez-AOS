package com.keyneez.presentation.login.phone

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.login.LoginActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentPhoneBinding

class PhoneFragment : BindingFragment<FragmentPhoneBinding>(R.layout.fragment_phone) {
    private val viewModel by viewModels<PhoneViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        initNextBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.btnPhoneBack.setOnSingleClickListener { requireActivity().finish() }
    }

    private fun initNextBtnClickListener() {
        binding.btnPhoneNext.setOnSingleClickListener {
            (activity as LoginActivity).intentToNextPage()
        }
    }

    companion object {
        fun newInstance() = PhoneFragment()
    }
}
