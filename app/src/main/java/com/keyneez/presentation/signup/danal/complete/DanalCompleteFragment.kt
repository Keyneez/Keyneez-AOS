package com.keyneez.presentation.signup.danal.complete

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.keyneez.presentation.signup.SignupActivity
import com.keyneez.util.binding.BindingFragment
import com.lab.keyneez.R
import com.lab.keyneez.databinding.FragmentDanalCompleteBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DanalCompleteFragment :
    BindingFragment<FragmentDanalCompleteBinding>(R.layout.fragment_danal_complete) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentToNextPage()
    }

    private fun intentToNextPage() {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            (activity as SignupActivity).intentToNextPage()
        }
    }

    companion object {
        fun newInstance() = DanalCompleteFragment()
    }
}
