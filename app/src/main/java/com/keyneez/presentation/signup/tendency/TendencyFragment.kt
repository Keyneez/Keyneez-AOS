package com.keyneez.presentation.signup.tendency

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentTendencyBinding

class TendencyFragment : BindingFragment<FragmentTendencyBinding>(R.layout.fragment_tendency) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = TendencyFragment()
    }
}
