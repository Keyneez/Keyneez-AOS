package com.keyneez.presentation.ocr.guide.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentOcrGuideBinding

class OcrGuideFragment : BindingFragment<FragmentOcrGuideBinding>(R.layout.fragment_ocr_guide) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ocr_guide, container, false)
    }

    companion object {
        fun newInstance() = OcrGuideFragment()
    }
}
