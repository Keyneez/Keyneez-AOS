package com.keyneez.presentation.ocr.dialog

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.keyneez.presentation.ocr.OcrActivity
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.binding.BindingBottomSheetDialog
import com.keyneez.util.extension.hideKeyboard
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.BotSheetOcrResultBinding

class OcrResultFragment :
    BindingBottomSheetDialog<BotSheetOcrResultBinding>(R.layout.bot_sheet_ocr_result) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = (activity as OcrActivity).viewModel

        initHideKeyboard()
        initReshootBtnClickListener()
        initConfirmBtnClickListener()
    }

    private fun initHideKeyboard() {
        binding.layoutOcrResult.setOnSingleClickListener {
            requireContext().hideKeyboard(it)
        }
    }

    private fun initReshootBtnClickListener() {
        binding.btnOcrResultReshoot.setOnSingleClickListener {
            dismiss()
        }
    }

    private fun initConfirmBtnClickListener() {
        binding.btnOcrResultConfirm.setOnSingleClickListener {
            val intent = Intent(activity, OcrGuideActivity::class.java).apply {
                requireActivity().setResult(RESULT_OK, this)
            }
            requireActivity().finish()
        }
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    companion object {
        fun newInstance(): OcrResultFragment {
            return OcrResultFragment()
        }
    }
}
