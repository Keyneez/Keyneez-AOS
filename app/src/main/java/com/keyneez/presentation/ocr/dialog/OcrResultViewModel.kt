package com.keyneez.presentation.ocr.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OcrResultViewModel @Inject constructor() : ViewModel() {
    private val _isStudentId = MutableLiveData<Boolean>()
    val isStudentId: LiveData<Boolean>
        get() = _isStudentId

    init {
        _isStudentId.value = true
    }

    val nameText = MutableLiveData("")
    val subEntryText = MutableLiveData("")

    /** ID 카드 유형 변경 */
    fun updateIdType(isStudent: Boolean) {
        _isStudentId.value = isStudent
        subEntryText.value = ""
    }
}
