package com.keyneez.presentation.ocr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OcrViewModel @Inject constructor() : ViewModel() {
    private val _isVertical = MutableLiveData<Boolean>()
    val isVertical: LiveData<Boolean>
        get() = _isVertical

    private val _isPassive = MutableLiveData<Boolean>()
    val isPassive: LiveData<Boolean>
        get() = _isPassive

    private val _isStudentId = MutableLiveData<Boolean>()
    val isStudentId: LiveData<Boolean>
        get() = _isStudentId

    init {
        _isVertical.value = false
        _isPassive.value = false
        _isStudentId.value = false
    }

    /** 카메라 프레임 회전 */
    fun updateCameraFrameOrientation() {
        _isVertical.value = !requireNotNull(_isVertical.value)
    }

    /** 신분증 인식 모드 변경 */
    fun updateRecognitionType() {
        _isPassive.value = !requireNotNull(_isPassive.value)
    }
}
