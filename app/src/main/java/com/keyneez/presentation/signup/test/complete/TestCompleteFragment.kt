package com.keyneez.presentation.signup.test.complete

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.keyneez.data.entity.ItemData
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
    private lateinit var testCompleteAdapter: TestCompleteAdapter

    val data = mutableListOf<ItemData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = (activity as SignupActivity).viewModel

//        setItemData()
        initTestCompleteAdapter()
        initNextBtnClickListener()
        initDetailBtnClickListener()
        initJellyDescriptionBottomSheet()
    }

    //    private fun setItemData() {
//        (activity as SignupActivity).viewModel.testResult.observe(viewLifecycleOwner) {
//            testCompleteAdapter.data = it.items ?: return@observe
//            testCompleteAdapter.notifyDataSetChanged()
//        }
//    }

    private fun initTestCompleteAdapter() {
        testCompleteAdapter = TestCompleteAdapter()
        binding.rvTestCompleteItem.adapter = testCompleteAdapter

        data.apply {
            add(ItemData(R.drawable.img_sign_up_board))
            add(ItemData(R.drawable.img_sign_up_headset))
            add(ItemData(R.drawable.img_sign_up_book))
            add(ItemData(R.drawable.img_sign_up_glasses))
            add(ItemData(R.drawable.img_sign_up_hat))
            add(ItemData(R.drawable.img_sign_up_money))
            add(ItemData(R.drawable.img_sign_up_ring))
            add(ItemData(R.drawable.img_sign_up_telescope))
            add(ItemData(R.drawable.img_sign_up_wing))
            add(ItemData(R.drawable.img_sign_up_tie))

            testCompleteAdapter.data = data
            testCompleteAdapter.notifyDataSetChanged()
        }
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

    private fun initJellyDescriptionBottomSheet() {
        bottomSheetBinding = BotSheetJellyDescriptionBinding.inflate(layoutInflater)
        bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
    }

    companion object {
        fun newInstance() = TestCompleteFragment()
    }
}
