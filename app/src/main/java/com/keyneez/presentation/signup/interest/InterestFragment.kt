package com.keyneez.presentation.signup.interest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentInterestBinding

class InterestFragment : BindingFragment<FragmentInterestBinding>(R.layout.fragment_interest) {
    private val viewModel by viewModels<InterestViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        initNextBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.btnInterestBack.setOnSingleClickListener {
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    private fun initNextBtnClickListener() {
        binding.btnInterestNext.setOnSingleClickListener {
            (activity as SignupActivity).intentToNextPage()
        }
    }

    companion object {
        fun newInstance() = InterestFragment()
    }
}
