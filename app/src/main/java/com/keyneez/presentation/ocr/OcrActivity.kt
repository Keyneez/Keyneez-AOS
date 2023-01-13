package com.keyneez.presentation.ocr

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.camera.core.* // ktlint-disable no-wildcard-imports
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
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.nio.ByteBuffer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class OcrActivity : BindingActivity<ActivityOcrBinding>(R.layout.activity_ocr) {
    val viewModel by viewModels<OcrViewModel>()

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var cameraProvider: ProcessCameraProvider
    private var imageCapture: ImageCapture? = null

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

                imageCapture = ImageCapture.Builder()
                    .build()

                // select default back camera
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
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
        imageCapture = imageCapture ?: return

        imageCapture!!.takePicture(
            cameraExecutor,
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    val bitmap = imageProxyToBitmap(image)
                    runTextRecognition(bitmap)
                    super.onCaptureSuccess(image)
                }

                override fun onError(exception: ImageCaptureException) {
                    Timber.tag(tag).e("exception : $exception")
                    showSnackbar(binding.root, getString(R.string.msg_error))
                    super.onError(exception)
                }
            }
        )
    }

    private fun imageProxyToBitmap(image: ImageProxy): Bitmap {
        val planeProxy = image.planes[0]
        val buffer: ByteBuffer = planeProxy.buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun runTextRecognition(img: Bitmap) {
        // 이미지 유형 : Bitmap, media.Image, ByteBuffer, byte array, device file
        val image = InputImage.fromBitmap(img, 0)
        val recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                processTextRecognitionResult(visionText)
            }
            .addOnFailureListener { e ->
                Timber.tag(tag).e("error : $e")
                showSnackbar(binding.root, getString(R.string.msg_error))
            }
    }

    private fun processTextRecognitionResult(text: Text) {
        if (text.textBlocks.size == 0) {
            Timber.tag(tag).e("인식된 글자 없음")
            showSnackbar(binding.root, "인식된 글자가 없습니다.")
            return
        }

        viewModel.resetIdInfo()
        var isSuccess = false

        for (block in text.textBlocks) {
            for (line in block.lines) {
                for (element in line.elements) {
                    val word = element.text
                    Timber.tag(tag).d("element : $word")

                    when (word) {
                        "학생증" -> {
                            viewModel.setIsStudent(true)
                            isSuccess = true
                        }
                        "청소년증" -> {
                            viewModel.setIsStudent(false)
                            isSuccess = true
                        }
                        else -> {
                            if (isSuccess && viewModel.idName.value == "" && word.length == 3) viewModel.setIdName(
                                word
                            )
                            if (isSuccess && viewModel.idName.value == "" && word.startsWith("명:") && word.length == 5) viewModel.setIdName(
                                word.substring(2, 5)
                            )
                            if (isSuccess && viewModel.idName.value == "" && word.startsWith(":") && word.length == 4) viewModel.setIdName(
                                word.substring(1, 4)
                            )
                            if (isSuccess && viewModel.isStudentId.value == true && viewModel.idSubEntry.value == "" && word.endsWith(
                                    "학교"
                                )
                            ) viewModel.setIdSchool(word)
                            if (isSuccess && viewModel.isStudentId.value == false && viewModel.idSubEntry.value == "" && word.length == 14 && word.contains(
                                    '-'
                                )
                            ) viewModel.setBirthDate(word)
                        }
                    }
                }
            }
        }

        if (isSuccess) {
            val ocrResultBottomSheet = OcrResultFragment()
            ocrResultBottomSheet.show(supportFragmentManager, ocrResultBottomSheet.tag)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val tag = "OCR_TEST"
    }
}
