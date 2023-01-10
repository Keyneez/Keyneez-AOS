package com.keyneez.presentation.main.home.recent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.data.entity.HomeData
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentHomeNewBinding

class NewFragment : BindingFragment<FragmentHomeNewBinding>(R.layout.fragment_home_new) {
    private val viewModel: NewViewModel by viewModels()
    val data = mutableListOf<HomeData>()
    private lateinit var NewAdapter: NewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHomeData()
        initNewAdapter()
    }

    private fun setupHomeData() {
        viewModel.itemList.observe(viewLifecycleOwner) {
            NewAdapter.data = it
            NewAdapter.notifyDataSetChanged()
        }
    }

    private fun initNewAdapter() {
        NewAdapter = NewAdapter()
        binding.rvNew.adapter = NewAdapter
    }

    companion object {
        fun newInstance(): NewFragment {
            return NewFragment()
        }
    }
}
