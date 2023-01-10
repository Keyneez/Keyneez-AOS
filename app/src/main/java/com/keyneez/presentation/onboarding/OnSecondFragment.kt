package com.keyneez.presentation.onboarding

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentOnSecondBinding

class OnSecondFragment : BindingFragment<FragmentOnSecondBinding>(R.layout.fragment_on_second) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    companion object {
        fun newInstance() = OnSecondFragment()
    }
}
