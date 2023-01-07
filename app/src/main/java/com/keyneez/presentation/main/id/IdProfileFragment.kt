package com.keyneez.presentation.main.id

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.BottomsheetIdProfileBinding

class IdProfileFragment : BottomSheetDialogFragment() {
    private var _binding: BottomsheetIdProfileBinding? = null
    private val binding: BottomsheetIdProfileBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetIdProfileBinding.inflate(inflater, container, false)
        return binding.root
        initIdPhotoClickListener()
    }

    fun initIdPhotoClickListener() {
        binding.btnIdProfilePhoto.setOnSingleClickListener {
            // 실물 인증 화면
            val intent = Intent(getActivity(), IdPhotoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): IdProfileFragment {
            return IdProfileFragment()
        }
    }
}
