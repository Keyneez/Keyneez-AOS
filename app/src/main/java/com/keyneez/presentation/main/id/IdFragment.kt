package com.keyneez.presentation.main.id

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentIdBinding

class IdFragment : BindingFragment<FragmentIdBinding>(R.layout.fragment_id) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (false) {
            // 발급하기 화면이 뜨게
            binding.layoutIdContent.visibility = View.VISIBLE
            binding.layoutIdMain.visibility = View.GONE
            initIdContentClickListener()
        } else {
            // 메인 아이디 화면이 뜨게
            binding.layoutIdContent.visibility = View.GONE
            binding.layoutIdMain.visibility = View.VISIBLE
            initIdMainClickListener()
        }
    }

    private fun initIdContentClickListener() {
        binding.btnIdIssue.setOnSingleClickListener {
            val intent = Intent(activity, OcrGuideActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initIdMainClickListener() {
        binding.btnIdMainArrow.setOnSingleClickListener {
            // 프로필
            val idProfileFragment = IdProfileFragment()
            idProfileFragment.show(childFragmentManager, "show")
        }
        binding.btnIdMainBenefit.setOnSingleClickListener {
            // 혜택안내
            val idBenefitFragment = IdBenefitFragment()
            idBenefitFragment.show(childFragmentManager, "show")
        }
        binding.btnIdMainPhoto.setOnSingleClickListener {
            // 실물 인증 화면
            val intent = Intent(activity, IdPhotoActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(): IdFragment {
            return IdFragment()
        }
    }
}
