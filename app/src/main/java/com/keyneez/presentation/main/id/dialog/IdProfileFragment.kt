package com.keyneez.presentation.main.id.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.util.binding.BindingBottomSheetDialog
import com.lab.keyneez.R
import com.lab.keyneez.databinding.BotSheetIdProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IdProfileFragment :
    BindingBottomSheetDialog<BotSheetIdProfileBinding>(R.layout.bot_sheet_id_profile) {
    private val viewModel by viewModels<IdProfileViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    companion object {
        fun newInstance(): IdProfileFragment {
            return IdProfileFragment()
        }
    }
}
