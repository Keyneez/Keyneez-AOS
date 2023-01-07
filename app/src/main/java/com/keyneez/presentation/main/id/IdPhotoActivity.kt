package com.keyneez.presentation.main.id

import android.os.Bundle
import android.widget.Toast
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
        }
        initIdBackClickListener()
    }

    private fun initIdBackClickListener() {
        val transcation = manager.beginTransaction()
        val idFragment = IdFragment()
        binding.btnIdPhotoBack.setOnSingleClickListener {
            // id 화면으로 이동
            transcation.replace(R.id.layout_id_main, idFragment)
            transcation.addToBackStack(null)
            transcation.commit()
        }
    }
}
