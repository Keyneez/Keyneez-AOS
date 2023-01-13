package com.keyneez.presentation.signup.test.complete

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.keyneez.presentation.main.like.LikeAdapter
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.BotSheetJellyDescriptionBinding
import com.lab.keyneez.databinding.FragmentTestCompleteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestCompleteFragment :
    BindingFragment<FragmentTestCompleteBinding>(R.layout.fragment_test_complete) {

    private lateinit var bottomSheetBinding: BotSheetJellyDescriptionBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var testCompleteAdapter: TestCompleteAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = (activity as SignupActivity).viewModel

        initNextBtnClickListener()
        initDetailBtnClickListener()
        initJellyDescriptionBottomSheet()
    }

    private fun initNextBtnClickListener() {
        binding.btnTestCompleteStartKeyneez.setOnSingleClickListener {
            (activity as SignupActivity).intentToNextPage()
        }
    }

    private fun initDetailBtnClickListener() {
        binding.btnTestCompleteDetail.setOnSingleClickListener {
            bottomSheetDialog.show()
        }
    }

    private fun setupTestCompleteData() {
        testCompleteAdapter.notifyDataSetChanged()
    }

    private fun initJellyDescriptionBottomSheet() {
        bottomSheetBinding = BotSheetJellyDescriptionBinding.inflate(layoutInflater)
        bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
    }

    private fun initTestCompleteAdapter() {
        testCompleteAdapter = TestCompleteAdapter()
        binding.rvTestCompleteItem.adapter = testCompleteAdapter
        binding.rvTestCompleteItem.layoutManager = RecyclerView.LayoutManager()
    }

    companion object {
        fun newInstance() = TestCompleteFragment()
    }
}
