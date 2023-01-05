package com.keyneez.presentation.main.home.recommend

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentHomeRecommendBinding

class RecommendInfoFragment :
    BindingFragment<FragmentHomeRecommendBinding>(R.layout.fragment_home_recommend) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): RecommendInfoFragment {
            return RecommendInfoFragment()
        }
    }
}
