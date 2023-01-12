package com.keyneez.presentation.signup.danal.complete

import android.os.Bundle
import android.view.View
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.avm = (activity as SignupActivity).viewModel

        intentToNextPage()
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
