package com.keyneez.presentation.main.home.recent

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentHomeNewBinding

class NewFragment : BindingFragment<FragmentHomeNewBinding>(R.layout.fragment_home_new) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): NewFragment {
            return NewFragment()
        }
    }
}
