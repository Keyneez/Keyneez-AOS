package com.keyneez.presentation.ocr

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityOcrBinding
import com.lab.keyneez.databinding.BotSheetAutoOcrResultBinding
import com.lab.keyneez.databinding.BotSheetPassiveOcrResultBinding
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class OcrActivity : BindingActivity<ActivityOcrBinding>(R.layout.activity_ocr) {
    private val viewModel by viewModels<OcrViewModel>()

    private lateinit var autoResultBinding: BotSheetAutoOcrResultBinding
    private lateinit var autoResultDialog: BottomSheetDialog
    private lateinit var passiveResultBinding: BotSheetPassiveOcrResultBinding
    private lateinit var passiveResultDialog: BottomSheetDialog

    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initCameraPreview()
        initCloseBtnClickListener()
        initCameraFrameView()
        initOcrTypeChangeBtn()
        initCameraBtnClickListener()
        initBottomSheet()
        initPassiveOcrResultBtnClickListeners()
    }

    private fun initCameraPreview() {
        cameraExecutor = Executors.newSingleThreadExecutor()

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(
            {
                // bind camera lifecycle
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(binding.previewOcr.surfaceProvider)
                }

                // select default back camera
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(this, cameraSelector, preview)
                } catch (e: Exception) {
                    Timber.e("$e : Use case binding failed")
                }
            },
            ContextCompat.getMainExecutor(this)
        )
    }

    private fun initCloseBtnClickListener() {
        binding.btnOcrClose.setOnSingleClickListener { finish() }
    }

    private fun initCameraFrameView() {
        binding.cfvOcrVertical.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        binding.cfvOcrHorizontal.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    private fun initOcrTypeChangeBtn() {
        binding.tvOcrPassiveOcr.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.tvOcrAutoOcr.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun initCameraBtnClickListener() {
        binding.btnOcrCamera.setOnSingleClickListener {
            passiveResultDialog.show()
        }
    }

    private fun initBottomSheet() {
        passiveResultBinding = BotSheetPassiveOcrResultBinding.inflate(layoutInflater)
        passiveResultDialog = BottomSheetDialog(this)
        passiveResultDialog.setContentView(passiveResultBinding.root)

        autoResultBinding = BotSheetAutoOcrResultBinding.inflate(layoutInflater)
        autoResultDialog = BottomSheetDialog(this)
        autoResultDialog.setContentView(autoResultBinding.root)
    }

    private fun initPassiveOcrResultBtnClickListeners() {
        passiveResultBinding.btnPassiveOcrResultReshoot.setOnSingleClickListener {
            passiveResultDialog.dismiss()
        }

        passiveResultBinding.btnPassiveOcrResultConfirm.setOnSingleClickListener {
            val intent = Intent(this, OcrGuideActivity::class.java).apply {
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
