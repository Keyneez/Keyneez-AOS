package com.keyneez.presentation.main.id

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.keyneez.presentation.main.id.dialog.IdBenefitFragment
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.BotSheetIdProfileBinding
import com.lab.keyneez.databinding.FragmentIdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IdFragment : BindingFragment<FragmentIdBinding>(R.layout.fragment_id) {

    private lateinit var bottomSheetBinding: BotSheetIdProfileBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private val viewModel by viewModels<IdViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initIdLayout()
        initBottomSheet()
        initIdPhotoBtnClickListener()
        initIdBackGround()
    }

    private fun initIdBackGround() {
        if (viewModel.userData.value?.userCharacter?.rem(5) == 1) {
            // 문화인-파란색
            binding.ivIdMainBackground.setImageDrawable(R.drawable.img_like_background)
        } else if (viewModel.userData.value?.userCharacter?.rem(5) == 2) {
            // 진로탐색러-초록색
            binding.ivIdMainBackground.setImageDrawable(R.drawable.img_like_background)
        } else if (viewModel.userData.value?.userCharacter?.rem(5) == 3) {
            // 탐험가-핑크색
            binding.ivIdMainBackground.setImageDrawable(R.drawable.img_like_background)
        } else if (viewModel.userData.value?.userCharacter?.rem(5) == 4) {
            // 경제인-빨간색
            binding.ivIdMainBackground.setImageDrawable(R.drawable.img_like_background)
        } else {
            // 봉사자-보라색
            binding.ivIdMainBackground.setImageDrawable(R.drawable.img_like_background)
        }
    }

    private fun initIdLayout() {
        if (viewModel.userData.value?.ocrImg == null) {
            // 발급하기 화면이 뜨게
            binding.layoutIdIssue.visibility = View.VISIBLE
            binding.layoutIdMain.visibility = View.GONE
            initIdIssueBtnClickListener()
        } else {
            // 메인 아이디 화면이 뜨게
            binding.layoutIdIssue.visibility = View.GONE
            binding.layoutIdMain.visibility = View.VISIBLE
            initIdMainBtnClickListener()
        }
    }
    private fun initBottomSheet() {
        bottomSheetBinding = BotSheetIdProfileBinding.inflate(layoutInflater)
        bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
    }

    private fun initIdPhotoBtnClickListener() {
        bottomSheetBinding.btnIdProfilePhoto.setOnSingleClickListener {
            // 실물 인증 화면
            val intent = Intent(getActivity(), IdPhotoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initIdIssueBtnClickListener() {
        binding.btnIdIssue.setOnSingleClickListener {
            val intent = Intent(activity, OcrGuideActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initIdMainBtnClickListener() {
        binding.btnIdMainArrow.setOnSingleClickListener {
            // 프로필
            bottomSheetDialog.show()
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

private fun ImageView.setImageDrawable(imgLikeBackground: Int) {
}
