package com.keyneez.presentation.main.id

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.keyneez.util.binding.BindingActivity
import com.keyneez.util.extension.setOnSingleClickListener
import com.lab.keyneez.R
import com.lab.keyneez.databinding.ActivityIdPhotoBinding

class IdPhotoActivity : BindingActivity<ActivityIdPhotoBinding>(R.layout.activity_id_photo) {
    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (true) {
            // 본인인증이 된 학생증
            Toast.makeText(getApplicationContext(), "OCR 본인인증이 완료된 학생증입니다", Toast.LENGTH_SHORT)
                .show()
            if (true) {
                // 세로로
                binding.cfvIdPhotoVertical.visibility = View.VISIBLE
                binding.cfvIdPhotoHorizontal.visibility = View.GONE
            } else {
                // 가로로
                binding.cfvIdPhotoHorizontal.visibility = View.VISIBLE
                binding.cfvIdPhotoVertical.visibility = View.GONE
            }
        }
        initIdBackClickListener()
    }

    private fun initIdBackClickListener() {
        binding.btnIdPhotoBack.setOnSingleClickListener {
            // id 화면으로 이동
            navigateTo<IdFragment>()
        }
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.layout_id_photo, T::class.java.canonicalName)
        }
    }
}
