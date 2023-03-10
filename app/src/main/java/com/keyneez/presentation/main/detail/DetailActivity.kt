package com.keyneez.presentation.main.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityHomeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity :
    BindingActivity<ActivityHomeDetailBinding>(R.layout.activity_home_detail) {
    private val detailViewModel: DetailViewModel by viewModels()

    private val contentId: Int by lazy { intent.getIntExtra("contentId", 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = detailViewModel

        initContentId()
        initBackClickListener()
        initShareClickListener()
    }

    private fun initContentId() {
        detailViewModel.getDetail(contentId)
    }

    private fun initBackClickListener() {
        binding.btnDetailBack.setOnSingleClickListener {
            finish()
        }
    }

    private fun initShareClickListener() {
        binding.btnDetailShare.setOnSingleClickListener { }
    }
}
