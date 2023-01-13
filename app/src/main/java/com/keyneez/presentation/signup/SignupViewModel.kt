package com.keyneez.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyneez.data.model.response.ResponsePatchUserTypeDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    private val _userName = MutableLiveData("")
    val userName: LiveData<String>
        get() = _userName

    private val _selectedTendency = MutableLiveData("")
    val selectedTendency: LiveData<String>
        get() = _selectedTendency

    private val _testResultItem = MutableLiveData<ResponsePatchUserTypeDto.Item>()
    val testResultItem: LiveData<ResponsePatchUserTypeDto.Item>
        get() = _testResultItem

    private val _testResult = MutableLiveData<ResponsePatchUserTypeDto>()
    val testResult: LiveData<ResponsePatchUserTypeDto>
        get() = _testResult

    /** 유저 이름 저장 */
    fun setUserName(name: String) {
        _userName.value = name
    }

    /** 선택한 관심사 저장 */
    fun setTendency(tendency: String) {
        _selectedTendency.value = tendency
    }

    /** 테스트 결과 저장 */
    fun setTestResult(responsePatchUserTypeDto: ResponsePatchUserTypeDto) {
        _testResult.value = responsePatchUserTypeDto
    }
}
