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

    private val _idName = MutableLiveData<String>()
    val idName: MutableLiveData<String>
        get() = _idName

    private val _idSubEntry = MutableLiveData<String>()
    val idSubEntry: MutableLiveData<String>
        get() = _idSubEntry

    private val _isStudentId = MutableLiveData<Boolean>()
    val isStudentId: LiveData<Boolean>
        get() = _isStudentId

    private val _imgUrl = MutableLiveData<String>()
    val imgUrl: LiveData<String>
        get() = _imgUrl

    init {
        _isVertical.value = false
        _isPassive.value = false
        _isStudentId.value = true
    }

    /** 카메라 프레임 회전 */
    fun updateCameraFrameOrientation() {
        _isVertical.value = !requireNotNull(_isVertical.value)
    }

    /** 신분증 인식 모드 변경 */
    fun updateRecognitionType() {
        _isPassive.value = !requireNotNull(_isPassive.value)
    }

    /** 신분증 이름 설정 */
    fun setIdName(name: String) {
        _idName.value = name
    }

    /** 신분증 학교 설정 */
    fun setIdSchool(school: String) {
        _idSubEntry.value = school
    }

    /** 생년월일 설정 */
    fun setBirthDate(date: String) {
        // date 형식 : 000000-0000000
        _idSubEntry.value = date.substring(0, 6)
    }

    /** 학생증 여부 판단 */
    fun setIsStudent(isStudent: Boolean) {
        _isStudentId.value = isStudent
    }

    /** ID 카드 유형 변경 */
    fun updateIdType(isStudent: Boolean) {
        _isStudentId.value = isStudent
        _idSubEntry.value = ""
    }

    /** 텍스트 추출 초기화 */
    fun resetIdInfo() {
        _idName.value = ""
        _idSubEntry.value = ""
    }
}
