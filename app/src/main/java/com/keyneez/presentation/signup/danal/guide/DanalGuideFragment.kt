package com.keyneez.presentation.signup.danal.guide

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentDanalGuideBinding

class DanalGuideFragment :
    BindingFragment<FragmentDanalGuideBinding>(R.layout.fragment_danal_guide) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = DanalGuideFragment()
    }
}
