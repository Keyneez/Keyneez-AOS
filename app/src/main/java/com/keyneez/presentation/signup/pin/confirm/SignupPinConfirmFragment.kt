package com.keyneez.presentation.signup.pin.confirm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.keyneez.presentation.main.MainActivity
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.presentation.signup.pin.SignupPinViewModel
import com.keyneez.util.binding.BindingFragment
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentSignupPinConfirmBinding

class SignupPinConfirmFragment :
    BindingFragment<FragmentSignupPinConfirmBinding>(R.layout.fragment_signup_pin_confirm) {
    private val viewModel by viewModels<SignupPinViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initBackBtnClickListener()
        setupPasswordText()
    }

    private fun initBackBtnClickListener() {
        binding.btnSignupPinConfirmBack.setOnSingleClickListener {
            viewModel.resetPassword()
            (activity as SignupActivity).intentToPreviousPage()
        }
    }

    private fun setupPasswordText() {
        viewModel.passwordText.observe(viewLifecycleOwner) { pwd ->
            if (pwd.length == 6) {
                // 이전 비밀번호와 비교 로직 필요
                // 서버 통신 - 유저 생성 (비밀번호)
                val toMain = Intent(activity, MainActivity::class.java)
                requireActivity().setResult(AppCompatActivity.RESULT_OK, toMain)
                startActivity(toMain)
                requireActivity().finish()
            }
        }
    }

    companion object {
        fun newInstance() = SignupPinConfirmFragment()
    }
}
