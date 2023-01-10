package com.keyneez.presentation.main.home.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.data.entity.HomeData
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentHomePopularBinding
import dagger.hilt.android.AndroidEntryPoint

class PopularFragment :
    BindingFragment<FragmentHomePopularBinding>(R.layout.fragment_home_popular) {
    private val viewModel: PopularViewModel by viewModels()
    val data = mutableListOf<HomeData>()
    private lateinit var PopularAdapter: PopularAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHomeData()
        initPopularAdapter()
    }

    private fun setupHomeData() {
        viewModel.itemList.observe(viewLifecycleOwner) {
            PopularAdapter.data = it
            PopularAdapter.notifyDataSetChanged()
        }
    }

    private fun initPopularAdapter() {
        PopularAdapter = PopularAdapter()
        binding.rvPopular.adapter = PopularAdapter
    }

    companion object {
        fun newInstance(): PopularFragment {
            return PopularFragment()
        }
    }
}
