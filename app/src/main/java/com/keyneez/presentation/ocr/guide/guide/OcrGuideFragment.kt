package com.keyneez.presentation.ocr.guide.guide

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.keyneez.presentation.ocr.OcrActivity
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showToast
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentOcrGuideBinding

class OcrGuideFragment : BindingFragment<FragmentOcrGuideBinding>(R.layout.fragment_ocr_guide) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBackBtnClickListener()
        initStartCameraBtnClickListener()
    }

    private fun initBackBtnClickListener() {
        binding.btnOcrGuideBack.setOnSingleClickListener { activity?.finish() }
    }

    private fun initStartCameraBtnClickListener() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                // text recognition success
                if (result.resultCode == Activity.RESULT_OK) {
                    (activity as OcrGuideActivity).intentToNextPage()
                }
            }

        val permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                // camera permission granted
                if (isGranted) {
                    val toOcr = Intent(context, OcrActivity::class.java)
                    resultLauncher.launch(toOcr)
                }
                // camera permission denied
                else {
                    requireContext().showToast(getString(R.string.ocr_guide_permission_denied_msg))
                }
            }

        binding.btnOcrGuideStartCamera.setOnSingleClickListener {
            if (permissionGranted()) permissionLauncher.launch(Manifest.permission.CAMERA)
            else requireContext().showToast(getString(R.string.ocr_guide_camera_permission_ungranted_msg))
        }
    }

    private fun permissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(),
            it
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        fun newInstance() = OcrGuideFragment()

        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.CAMERA
        ).toTypedArray()
    }
}
