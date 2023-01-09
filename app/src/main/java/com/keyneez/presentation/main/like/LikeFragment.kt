package com.keyneez.presentation.main.like

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.keyneez.data.entity.LikeData
import com.keyneez.presentation.main.search.SearchActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentLikeBinding

class LikeFragment : BindingFragment<FragmentLikeBinding>(R.layout.fragment_like) {
    lateinit var likeAdapter: LikeAdapter
    val data = mutableListOf<LikeData>()
    val viewModel: LikeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLikeAdapter()
        initLikeSaveClickListener()
        initLikeSearchClickListener()
        setupLikeData()
    }

    private fun initLikeSearchClickListener() {
        binding.btnLikeSearch.setOnSingleClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initLikeSaveClickListener() {
        binding.btnLikeSave.setOnSingleClickListener {
            binding.rvLikeContent.smoothScrollToPosition(0)
        }
    }

    private fun setupLikeData() {
        viewModel.likeList.observe(viewLifecycleOwner) {
            likeAdapter.data = it
            likeAdapter.notifyDataSetChanged()
        }
    }

    private fun initLikeAdapter() {
        likeAdapter = LikeAdapter()
        binding.rvLikeContent.adapter = likeAdapter
        binding.rvLikeContent.layoutManager = GridLayoutManager(activity, 2)
    }

    companion object {
        fun newInstance(): LikeFragment {
            return LikeFragment()
        }
    }
}
