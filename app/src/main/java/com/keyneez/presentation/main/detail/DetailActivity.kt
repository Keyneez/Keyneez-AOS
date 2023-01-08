package com.keyneez.presentation.main.detail

import android.os.Bundle
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityHomeDetailBinding

class DetailActivity :
    BindingActivity<ActivityHomeDetailBinding>(R.layout.activity_home_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // 뒤로가기 버튼
    private fun initBackClickListener() {
        binding.btnDetailBack.setOnSingleClickListener { }
    }

    // 공유 버튼
    private fun initShareClickListener() {
        binding.btnDetailShare.setOnSingleClickListener { }
    }

    // 좋아요 버튼
    private fun initHeartClickListener() {
        binding.btnDetailHeart.setOnSingleClickListener { }
    }
}
