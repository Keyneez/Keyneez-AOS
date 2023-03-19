package com.keyneez.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keyneez.presentation.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intentToOnboarding()
    }

    private fun intentToOnboarding() {
        startActivity(Intent(this, OnboardingActivity::class.java))
        finish()
    }
}
