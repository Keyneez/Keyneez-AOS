package com.keyneez.presentation.signup.test.complete

import android.os.Bundle
import android.view.View
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentTestCompleteBinding

class TestCompleteFragment :
    BindingFragment<FragmentTestCompleteBinding>(R.layout.fragment_test_complete) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNextBtnClickListener()
    }

    private fun initNextBtnClickListener() {
        binding.btnTestCompleteStartKeyneez.setOnSingleClickListener {
            (activity as SignupActivity).intentToNextPage()
        }
    }

    companion object {
        fun newInstance() = TestCompleteFragment()
    }
}
