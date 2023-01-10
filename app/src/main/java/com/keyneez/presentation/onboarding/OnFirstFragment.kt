package com.keyneez.presentation.onboarding

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentOnFirstBinding

class OnFirstFragment : BindingFragment<FragmentOnFirstBinding>(R.layout.fragment_on_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = OnFirstFragment()
    }
}
