package com.keyneez.presentation.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.keyneez.presentation.main.home.neww.NewInfoFragment
import com.keyneez.presentation.main.home.popular.PopularInfoFragment
import com.keyneez.presentation.main.home.recommend.RecommendFragment

class HomeAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return RecommendFragment()
            1 -> return PopularInfoFragment()
            2 -> return NewInfoFragment()
        }
        return RecommendFragment()
    }
}
