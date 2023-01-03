package com.keyneez.presentation.ocr

import android.os.Bundle
import android.view.View
import com.keyneez.util.binding.BindingActivity
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityOcrBinding

class OcrActivity : BindingActivity<ActivityOcrBinding>(R.layout.activity_ocr) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initCameraFrame()
    }

    private fun initCameraFrame() {
        binding.cfvOcr.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }
}
