package com.keyneez.presentation.signup.test.guide

import android.os.Bundle
import android.view.View
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentTestGuideBinding

class TestGuideFragment : BindingFragment<FragmentTestGuideBinding>(R.layout.fragment_test_guide) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initStartBtnClickListener()
    }

    private fun initStartBtnClickListener() {
        binding.btnTestGuideStart.setOnSingleClickListener {
            (activity as SignupActivity).intentToNextPage()
        }
    }

    companion object {
        fun newInstance() = TestGuideFragment()
    }
}
