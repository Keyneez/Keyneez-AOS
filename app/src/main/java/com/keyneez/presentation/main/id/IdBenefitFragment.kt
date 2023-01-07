package com.keyneez.presentation.main.id

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.BottomsheetIdBenefitBinding

class IdBenefitFragment : BottomSheetDialogFragment() {
    private var _binding: BottomsheetIdBenefitBinding? = null
    private val binding: BottomsheetIdBenefitBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetIdBenefitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    companion object {
        fun newInstance(): IdBenefitFragment {
            return IdBenefitFragment()
        }
    }
}
