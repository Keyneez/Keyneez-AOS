package com.keyneez.presentation.signup.test.complete

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentTestCompleteBinding

class TestCompleteFragment : BindingFragment<FragmentTestCompleteBinding>(R.layout.fragment_test_complete) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = TestCompleteFragment()
    }
}
