package com.keyneez.presentation.signup.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupOtpViewModel @Inject constructor() : ViewModel() {
    private val _passwordText = MutableLiveData("")
    val passwordText: LiveData<String>
        get() = _passwordText

    private val _keypadList = MutableLiveData<List<Int>>()
    val keypadList: LiveData<List<Int>>
        get() = _keypadList

    init {
        rearrangeKeypad()
    }

    /** 숫자 재배열 */
    fun rearrangeKeypad() {
        val numbers = linkedSetOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val tempKeypadList = mutableListOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1)

        for (i in 9 downTo 0) {
            val randomNum = numbers.random()
            tempKeypadList[i] = randomNum
            numbers.remove(randomNum)
        }

        _keypadList.value = tempKeypadList
    }

    /** 비밀번호 입력 */
    fun editPassword(number: Int) {
        _passwordText.value += _keypadList.value?.get(number)
    }

    /** 비밀번호 지우기 */
    fun erasePassword() {
        if (_passwordText.value?.isEmpty() == true) return
        _passwordText.value =
            _passwordText.value?.length?.let { _passwordText.value?.substring(0, it.minus(1)) }
    }

    /** 비밀번호 초기화 */
    fun resetPassword() {
        rearrangeKeypad()
        _passwordText.value = ""
    }
}
