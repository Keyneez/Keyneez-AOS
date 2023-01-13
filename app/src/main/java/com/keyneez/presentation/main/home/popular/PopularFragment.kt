package com.keyneez.presentation.main.home.popular

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentHomePopularBinding

class PopularFragment :
    BindingFragment<FragmentHomePopularBinding>(R.layout.fragment_home_popular) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): PopularFragment {
            return PopularFragment()
        }
    }
}
