package com.keyneez.presentation.ocr.guide.complete

import android.os.Bundle
import android.view.View
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentOcrCompleteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OcrCompleteFragment :
    BindingFragment<FragmentOcrCompleteBinding>(R.layout.fragment_ocr_complete) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBackBtnClickListener()
        initConfirmBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.btnOcrCompleteBack.setOnSingleClickListener {
            (activity as OcrGuideActivity).intentToPreviousPage()
        }
    }

    private fun initConfirmBtnClickListener() {
        binding.btnOcrCompleteConfirm.setOnSingleClickListener { activity?.finish() }
    }

    companion object {
        fun newInstance() = OcrCompleteFragment()
    }
}
