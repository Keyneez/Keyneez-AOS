package com.keyneez.presentation.main.id.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.BotSheetIdBenefitBinding

class IdBenefitFragment : BottomSheetDialogFragment() {
    private var _binding: BotSheetIdBenefitBinding? = null
    private val binding: BotSheetIdBenefitBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BotSheetIdBenefitBinding.inflate(inflater, container, false)
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
