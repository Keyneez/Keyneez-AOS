package com.keyneez.presentation.signup.danal.complete

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentDanalCompleteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DanalCompleteFragment :
    BindingFragment<FragmentDanalCompleteBinding>(R.layout.fragment_danal_complete) {
    private val viewModel by viewModels<DanalCompleteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        initUserName()
        intentToNextPage()
    }

    private fun initUserName() {
        viewModel.initUserName()
    }

    private fun intentToNextPage() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000)
            (activity as SignupActivity).intentToNextPage()
        }
    }

    companion object {
        fun newInstance() = DanalCompleteFragment()
    }
}
