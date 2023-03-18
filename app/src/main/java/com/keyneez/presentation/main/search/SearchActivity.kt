package com.keyneez.presentation.main.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
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
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initLikeAdapter()
        setupSearchDataState()
        initHideKeyboard()
        initSearchBackBtnClickListener()
        setupSearchState()
        initSearchBtnClickListener()
    }

    private fun initSearchBtnClickListener() {
        binding.btnSearchResult.setOnKeyListener { v, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                viewModel.getSearchPostData()
                setupSearchDataState()
                true
            } else {
                false
            }
        }
        binding.etSearchContent.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.getSearchPostData()
                    setupSearchDataState()
                    return true
                }
                return false
            }
        })
    }
    private fun setupSearchDataState() {
        viewModel.searchList.observe(this) {
            searchAdapter.data = it
            searchAdapter.notifyDataSetChanged()
            binding.tvSearchCount.setText(it.size.toString())
            if (it.size.toString() == "0") {
                Toast.makeText(this, R.string.search_no_result, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun setupSearchState() {
        viewModel.stateMessage.observe(this) {
            when (it) {
                is UiState.Success -> setupSearchDataState()
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
