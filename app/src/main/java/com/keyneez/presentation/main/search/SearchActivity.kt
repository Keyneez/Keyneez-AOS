package com.keyneez.presentation.main.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivitySearchBinding

class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    lateinit var searchAdapter: SearchAdapter
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initLikeAdapter()
        setupSearchData()
        initSearchBackBtnClickListener()
    }

    private fun setupSearchData() {
        viewModel.searchList.observe(this) {
            searchAdapter.data = it
            searchAdapter.notifyDataSetChanged()
        }
    }

    private fun initLikeAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvSearchResultContent.adapter = searchAdapter
        binding.rvSearchResultContent.layoutManager = GridLayoutManager(this, 2)
    }
    private fun initSearchBackBtnClickListener() {
        binding.btnSearchBack.setOnSingleClickListener {
            finish()
        }
    }
}
