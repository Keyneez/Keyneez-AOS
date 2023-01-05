package com.keyneez.presentation.signup

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.keyneez.util.binding.BindingActivity
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivitySignupBinding

class SignupActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignupViewPager()
    }

    private fun initSignupViewPager() {
        viewPager = binding.vpSignup
        binding.vpSignup.adapter = SignupAdapter(this)
        // binding.vpSignup.isUserInputEnabled = false // disable swipe
    }

    /** 해당 인덱스의 프래그먼트로 ViewPager 전환 */
    fun selectIndex(newIndex: Int) {
        binding.vpSignup.currentItem = newIndex
    }
}
