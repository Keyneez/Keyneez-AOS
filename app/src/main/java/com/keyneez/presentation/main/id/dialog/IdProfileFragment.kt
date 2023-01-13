package com.keyneez.presentation.main.id.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingBottomSheetDialog
import com.keyneez.util.extension.showSnackbar
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
        observeIdProfileStateMessage()
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    private fun observeIdProfileStateMessage() {
        viewModel.stateMessage.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> return@observe
                is UiState.Failure -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_profile_null)
                )
                is UiState.Error -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
            }
        }
    }

    companion object {
        fun newInstance(): IdProfileFragment {
            return IdProfileFragment()
        }
    }
}
