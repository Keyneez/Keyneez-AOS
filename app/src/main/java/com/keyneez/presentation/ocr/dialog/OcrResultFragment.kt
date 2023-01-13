package com.keyneez.presentation.ocr.dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.presentation.ocr.OcrActivity
import com.keyneez.presentation.ocr.dialog.OcrResultViewModel.Companion.CHECK_FAIL_CODE
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingBottomSheetDialog
import com.keyneez.util.extension.hideKeyboard
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.keyneez.util.extension.showToast
import com.lab.keyneez.R
import com.lab.keyneez.databinding.BotSheetOcrResultBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class OcrResultFragment :
    BindingBottomSheetDialog<BotSheetOcrResultBinding>(R.layout.bot_sheet_ocr_result) {
    private val viewModel by viewModels<OcrResultViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = (activity as OcrActivity).viewModel

        initHideKeyboard()
        initReshootBtnClickListener()
        initConfirmBtnClickListener()
        setupCheckUserState()
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
            val avm = (activity as OcrActivity).viewModel
            val isStudent = avm.isStudentId.value ?: true
            val name = avm.idName.value.toString()
            val subEntry = avm.idSubEntry.value.toString()
            val img = avm.imgUrl.value.toString()
            val isVertical = avm.isVertical.value ?: false

            viewModel.postUserCheck(isStudent, name, subEntry, img, isVertical)
        }
    }

    private fun setupCheckUserState() {
        viewModel.stateMessage.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    val intent = Intent(activity, OcrGuideActivity::class.java).apply {
                        requireActivity().setResult(Activity.RESULT_OK, this)
                    }
                    requireActivity().finish()
                }
                is UiState.Failure -> {
                    when (it.code) {
                        CHECK_FAIL_CODE -> {
                            requireContext().showToast(getString(R.string.ocr_result_check_fail_msg))
                        }
                        else -> requireContext().showSnackbar(
                            binding.root,
                            getString(R.string.msg_error)
                        )
                    }
                }
                is UiState.Error -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_error)
                )
            }
        }
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    companion object {
        fun newInstance(): OcrResultFragment {
            return OcrResultFragment()
        }
    }
}
