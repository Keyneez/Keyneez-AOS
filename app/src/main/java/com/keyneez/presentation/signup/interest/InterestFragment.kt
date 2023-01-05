package com.keyneez.presentation.signup.interest

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentInterestBinding

class InterestFragment : BindingFragment<FragmentInterestBinding>(R.layout.fragment_interest) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = InterestFragment()
    }
}
