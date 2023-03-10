package com.keyneez.presentation.main.id

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityIdPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IdPhotoActivity : BindingActivity<ActivityIdPhotoBinding>(R.layout.activity_id_photo) {
    private val viewModel by viewModels<IdPhotoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        Toast.makeText(getApplicationContext(), "OCR 본인인증이 완료된 학생증입니다", Toast.LENGTH_SHORT).show()
        // 본인인증이 된 학생증
        initIdPhotoFrameView()
        initIdBackBtnClickListener()
        observeIdPhotoStateMessage()
    }

    private fun observeIdPhotoStateMessage() {
        viewModel.stateMessage.observe(this) {
            when (it) {
                is UiState.Success -> if (viewModel.userData.value?.ocrDir == true) {
                    // 세로로
                    binding.cfvIdPhotoVertical.visibility = View.VISIBLE
                    binding.cfvIdPhotoHorizontal.visibility = View.GONE
                } else {
                    // 가로로
                    binding.cfvIdPhotoHorizontal.visibility = View.VISIBLE
                    binding.cfvIdPhotoVertical.visibility = View.GONE
                }
                is UiState.Failure -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_photo_null)
                )
                is UiState.Error -> showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
            }
        }
    }

    private fun initIdPhotoFrameView() {
        binding.cfvIdPhotoHorizontal.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        binding.cfvIdPhotoVertical.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    private fun initIdBackBtnClickListener() {
        binding.btnIdPhotoBack.setOnSingleClickListener {
            finish()
        }
    }
}
