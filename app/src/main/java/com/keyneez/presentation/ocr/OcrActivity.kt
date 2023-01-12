package com.keyneez.presentation.ocr

import android.graphics.Bitmap
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import com.keyneez.presentation.ocr.dialog.OcrResultFragment
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityOcrBinding
import timber.log.Timber
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class OcrActivity : BindingActivity<ActivityOcrBinding>(R.layout.activity_ocr) {
    private val viewModel by viewModels<OcrViewModel>()

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var cameraProvider: ProcessCameraProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initCameraPreview()
        initCloseBtnClickListener()
        initCameraFrameView()
        initOcrTypeChangeBtn()
        initCameraBtnClickListener()
    }

    private fun initCameraPreview() {
        cameraExecutor = Executors.newSingleThreadExecutor()

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(
            {
                // bind camera lifecycle
                cameraProvider = cameraProviderFuture.get()

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
            takePhoto()
        }
    }

    private fun takePhoto() {
        val imageCapture = ImageCapture.Builder().build()
    }

    // Text Recognition
    // CameraX -> OnImageCapturedListener & ImageAnalysis.Analyzer 활용해서 rotation 계산

    private fun runTextRecognition(img: Bitmap) {
        // 이미지 유형 : Bitmap, media.Image, ByteBuffer, byte array, device file
        val image = InputImage.fromBitmap(img, 0)
        val recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                processTextRecognitionResult(visionText)
            }
            .addOnFailureListener { e ->
                showSnackbar(binding.root, getString(R.string.msg_error))
            }
    }

    private fun processTextRecognitionResult(text: Text) {
        val ocrResultBottomSheet = OcrResultFragment()
        ocrResultBottomSheet.show(supportFragmentManager, ocrResultBottomSheet.tag)

        if (text.textBlocks.size == 0) {
            Timber.e("인식된 글자 없음")
            showSnackbar(binding.root, "인식된 글자가 없습니다.")
            return
        }

        for (block in text.textBlocks) {
            Timber.d("block : $block")

            for (line in block.lines) {
                Timber.d("line : $line")

                for (element in line.elements)
                    Timber.d("element : $element")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
