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
    val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = detailViewModel

        initContentId()
        initBackClickListener()
        initShareClickListener()
        initHeartClickListener()
    }

    private fun initContentId() {
        detailViewModel.getDetail(2)
    }

    private fun initBackClickListener() {
        binding.btnDetailBack.setOnSingleClickListener { }
    }

    private fun initShareClickListener() {
        binding.btnDetailShare.setOnSingleClickListener { }
    }

    private fun initHeartClickListener() {
        binding.btnDetailHeart.setOnSingleClickListener { }
    }
}
