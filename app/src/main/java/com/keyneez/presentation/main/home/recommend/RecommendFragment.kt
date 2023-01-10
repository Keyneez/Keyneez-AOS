package com.keyneez.presentation.main.home.recommend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.data.entity.HomeData
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentHomeRecommendBinding

class RecommendFragment :
    BindingFragment<FragmentHomeRecommendBinding>(R.layout.fragment_home_recommend) {
    private val viewModel: RecommendViewModel by viewModels()
    val data = mutableListOf<HomeData>()
    private lateinit var RecommendAdapter: RecommendAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHomeData()
        initRecommendAdapter()
    }

    private fun setupHomeData() {
        viewModel.itemList.observe(viewLifecycleOwner) {
            RecommendAdapter.data = it
            RecommendAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecommendAdapter() {
        RecommendAdapter = RecommendAdapter()
        binding.rvRecommend.adapter = RecommendAdapter
    }

    companion object {
        fun newInstance(): RecommendFragment {
            return RecommendFragment()
        }
    }
}
