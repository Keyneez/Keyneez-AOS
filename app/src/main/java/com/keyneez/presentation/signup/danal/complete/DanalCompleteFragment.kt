package com.keyneez.presentation.signup.danal.complete

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentDanalCompleteBinding

class DanalCompleteFragment : BindingFragment<FragmentDanalCompleteBinding>(R.layout.fragment_danal_complete) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = DanalCompleteFragment()
    }
}
