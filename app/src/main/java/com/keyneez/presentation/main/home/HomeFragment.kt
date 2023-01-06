package com.keyneez.presentation.main.home

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentHomeBinding

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHomeViewPagerAdapter()
    }

    private fun initHomeViewPagerAdapter() {
        val viewPager = binding.vpHome
        val tabLayout = binding.tabHome

        val homeTabTitles = listOf<String>(
            getString(R.string.home_recommend),
            getString(R.string.home_popular),
            getString(R.string.home_new)
        )

        viewPager.adapter = HomeAdapter(parentFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = homeTabTitles[position]
        }.attach()
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}
