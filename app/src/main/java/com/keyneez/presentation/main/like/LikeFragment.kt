package com.keyneez.presentation.main.like

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.keyneez.data.entity.LikeData
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentLikeBinding

class LikeFragment : BindingFragment<FragmentLikeBinding>(R.layout.fragment_like) {
    // newinstance() 사용하기
    // private val likeViewModel by viewModels<LikeViewModel>()
    lateinit var likeAdapter: LikeAdapter
    val data = mutableListOf<LikeData>()
    val viewModel: LikeViewModel by viewModels()
    // liveData는 observer 패턴을 기반으로 하며 뷰모델과 뷰 간의 통신을 쉽게 한다.
    // 또한 데이터 변경을 관찰하고 데이터를 자동으로 업데이트한다.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLikeAdapter()
        initLikeSaveClickListener()
        initLikeSearchClickListener()
        setupLikeData()
    }

    private fun initLikeSearchClickListener() {
        binding.btnLikeSearch.setOnClickListener {
            // 검색화면으로 이동
        }
    }

    private fun initLikeSaveClickListener() {
        binding.btnLikeSave.setOnClickListener {
            binding.rvLikeContent.smoothScrollToPosition(0)
        }
    }

    private fun setupLikeData() {
        // ViewModel을 observe하는 부분을 사용하면 된다.
        // 뷰모델에서 likelist가 바뀔 때마다 자동적으로 호출되어 바꿔줌
        // viewLifecycleOwner 대신에 액티비티에서는 this를 사용해 준다.
        // 상태 변경을 자동으로 알린다.
        viewModel.likeList.observe(viewLifecycleOwner) {
            // 어댑터에서 mutablelist썼으면 뷰홀더에서도 mutablelist를 사용하고 어댑터에서 listOf를 썼으면 뷰홀더에서도 통일하기
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
