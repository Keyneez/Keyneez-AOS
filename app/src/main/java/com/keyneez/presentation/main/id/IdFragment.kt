package com.keyneez.presentation.main.id

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.keyneez.presentation.main.id.dialog.IdBenefitFragment
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.UiState
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.keyneez.util.extension.showSnackbar
import com.lab.keyneez.R
import com.lab.keyneez.databinding.BotSheetIdProfileBinding
import com.lab.keyneez.databinding.FragmentIdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IdFragment : BindingFragment<FragmentIdBinding>(R.layout.fragment_id) {

    private lateinit var bottomSheetBinding: BotSheetIdProfileBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    val viewModel by viewModels<IdViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBottomSheet()
        initIdPhotoBtnClickListener()
        initIdIssueBtnClickListener()
        initIdMainBtnClickListener()
        observeIdStateMessage()
    }

    private fun observeIdStateMessage() {
        viewModel.stateMessage.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Success -> {
                    // ID를 발급하지 않은 경우
                    if (viewModel.userData.value?.ocrImg == null) {
                        binding.layoutIdIssue.visibility = View.VISIBLE
                        binding.layoutIdMain.visibility = View.GONE
                    }
                    // ID를 발급한 경우
                    else {
                        // 메인 아이디 화면이 뜨게
                        binding.layoutIdIssue.visibility = View.GONE
                        binding.layoutIdMain.visibility = View.VISIBLE
                        initIdBackGround()
                    }
                }
                is UiState.Failure -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_id_null)
                )
                is UiState.Error -> requireContext().showSnackbar(
                    binding.root,
                    getString(R.string.msg_server_error)
                )
            }
        }
    }

    private fun initIdBackGround() {
        when (viewModel.userData.value?.userCharacter?.rem(5)) {
            // 문화인 - 파란색
            1 -> binding.ivIdMainBackground.setImageResource(R.mipmap.card_bg_mint)
            // 진로탐색러 - 초록색
            2 -> binding.ivIdMainBackground.setImageResource(R.mipmap.card_bg_green)
            // 탐험가 - 핑크색
            3 -> binding.ivIdMainBackground.setImageResource(R.mipmap.card_bg_pink)
            // 경제인 - 빨간색
            4 -> binding.ivIdMainBackground.setImageResource(R.mipmap.card_bg_red)
            // 봉사자 - 보라색
            else -> binding.ivIdMainBackground.setImageResource(R.mipmap.card_bg_purple)
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
            val intent = Intent(activity, IdPhotoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initIdIssueBtnClickListener() {
        binding.btnIdIssue.setOnSingleClickListener {
            val intent = Intent(activity, OcrGuideActivity::class.java)
            startActivity(intent)
            // launcher로 반환값에 따라 화면 다르게 처리 필요
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
