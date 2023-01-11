package com.keyneez.presentation.signup.tendency

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentTendencyBinding

class TendencyFragment : BindingFragment<FragmentTendencyBinding>(R.layout.fragment_tendency) {
    private val viewModel by viewModels<TendencyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        initNextBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.btnTendencyBack.setOnSingleClickListener {
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    private fun initNextBtnClickListener() {
        binding.btnTendencyNext.setOnSingleClickListener {
            (activity as SignupActivity).setTendency(requireNotNull(viewModel.selectedTendency.value))
            (activity as SignupActivity).intentToNextPage()
        }
    }

    companion object {
        fun newInstance() = TendencyFragment()
    }
}
