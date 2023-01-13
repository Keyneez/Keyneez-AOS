package com.keyneez.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.keyneez.presentation.main.character.CharacterFragment
import com.keyneez.presentation.main.home.HomeFragment
import com.keyneez.presentation.main.id.IdFragment
import com.keyneez.presentation.main.like.LikeFragment
import com.keyneez.presentation.main.setting.SettingFragment
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initBottomNavigationBar()
        initCardBackGround()
    }

    private fun initBottomNavigationBar() {
        // 초기 프래그먼트 설정
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container_main)
        if (currentFragment == null) navigateTo<HomeFragment>()

        // 메뉴 클릭 시 프래그먼트 전환
        binding.botNavMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> navigateTo<HomeFragment>()
                R.id.menu_like -> navigateTo<LikeFragment>()
                R.id.menu_character -> navigateTo<CharacterFragment>()
                R.id.menu_setting -> navigateTo<SettingFragment>()
            }
            true
        }
    }

    private fun initCardBackGround() {
        binding.btnMainCard.setOnSingleClickListener {
            navigateTo<IdFragment>()
        }
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.container_main, T::class.java.canonicalName)
        }
    }
}
