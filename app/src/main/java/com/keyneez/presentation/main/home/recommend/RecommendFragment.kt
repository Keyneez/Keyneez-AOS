package com.keyneez.presentation.main.home.recommend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.keyneez.data.model.response.ResponseContentDto
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentHomeRecommendBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecommendFragment :
    BindingFragment<FragmentHomeRecommendBinding>(R.layout.fragment_home_recommend) {
    private val viewModel: RecommendViewModel by viewModels()
    val data = mutableListOf<ResponseContentDto>()
    private lateinit var recommendAdapter: RecommendAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        setHomeData()
        initRecommendAdapter()
    }

    private fun setHomeData() {
        viewModel.contentList.observe(viewLifecycleOwner) {
            recommendAdapter.data = it
            recommendAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecommendAdapter() {
        recommendAdapter = RecommendAdapter()
        binding.rvRecommend.adapter = recommendAdapter
        binding.rvRecommend.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {
        fun newInstance(): RecommendFragment {
            return RecommendFragment()
        }
    }
}
