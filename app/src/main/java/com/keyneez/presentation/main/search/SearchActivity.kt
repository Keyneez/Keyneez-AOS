package com.keyneez.presentation.main.search

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.keyneez.data.model.response.ResponseGetSearchDto
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.hideKeyboard
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivitySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    lateinit var searchAdapter: SearchAdapter
    val data = mutableListOf<ResponseGetSearchDto>()
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initLikeAdapter()
        setupSearchData()
        initHideKeyboard()
        initSearchBackBtnClickListener()
        observeSearchStateMessage()
    }

    private fun setupSearchData() {
        viewModel.searchList.observe(this) {
            searchAdapter.data = it
            searchAdapter.notifyDataSetChanged()
            binding.tvSearchCount.setText(it.size.toString())
            if(it.size.toString()=="0"){
                Toast.makeText(this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun observeSearchStateMessage() {
        viewModel.stateMessage.observe(this) {
            when (it) {
                is UiState.Success -> setupSearchData()
                is UiState.Failure -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_search_null)
                )
                is UiState.Error -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
            }
        }
    }

    private fun initHideKeyboard() {
        binding.layoutSearchResult.setOnSingleClickListener {
            hideKeyboard()
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
