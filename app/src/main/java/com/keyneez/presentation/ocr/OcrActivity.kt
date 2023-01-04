package com.keyneez.presentation.ocr

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityOcrBinding

class OcrActivity : BindingActivity<ActivityOcrBinding>(R.layout.activity_ocr) {
    private val viewModel by viewModels<OcrViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initCloseBtnClickListener()
        initHorizontalCameraFrameView()
        initOcrTypeChangeBtn()
        initCameraBtnClickListener()
    }

    private fun initCloseBtnClickListener() {
        binding.btnOcrClose.setOnSingleClickListener { finish() }
    }

    private fun initHorizontalCameraFrameView() {
        binding.cfvOcrVertical.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        binding.cfvOcrHorizontal.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    private fun initOcrTypeChangeBtn() {
        binding.tvOcrPassiveOcr.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.tvOcrAutoOcr.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    /** 뷰 연결을 위한 임시 버튼 설정 */
    private fun initCameraBtnClickListener() {
        binding.btnOcrCamera.setOnSingleClickListener {
            val intent = Intent(this, OcrGuideActivity::class.java).apply {
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }
}
