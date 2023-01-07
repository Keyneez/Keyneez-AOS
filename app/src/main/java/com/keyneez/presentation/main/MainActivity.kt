package com.keyneez.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.keyneez.presentation.main.character.CharacterFragment
import com.keyneez.presentation.main.home.HomeFragment
import com.keyneez.presentation.main.id.IdFragment
import com.keyneez.presentation.main.like.LikeFragment
import com.keyneez.presentation.main.setting.SettingFragment
import com.keyneez.presentation.ocr.guide.OcrGuideActivity
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigationBar()
        initCardBtnClickListener()
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
                R.id.menu_card -> navigateTo<IdFragment>()
            }
            true
        }

        binding.btnMainCard.setOnClickListener(
            View.OnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layout_id, IdFragment())
                    .commit()
            }
        )
    }

    private fun initCardBtnClickListener() {
        binding.btnMainCard.setOnSingleClickListener {
            val intent = Intent(this, OcrGuideActivity::class.java)
            startActivity(intent)
        }
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.container_main, T::class.java.canonicalName)
        }
    }
}
