package com.keyneez.presentation.ocr.guide.complete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentOcrCompleteBinding

class OcrCompleteFragment :
    BindingFragment<FragmentOcrCompleteBinding>(R.layout.fragment_ocr_complete) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ocr_complete, container, false)
    }

    companion object {
        fun newInstance() = OcrCompleteFragment()
    }
}
