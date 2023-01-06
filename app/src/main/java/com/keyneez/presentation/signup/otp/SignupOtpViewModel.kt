package com.keyneez.presentation.signup.otp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupOtpViewModel @Inject constructor() : ViewModel() {
    private val _passwordText = MutableLiveData<String>("")
    val passwordText: LiveData<String>
        get() = _passwordText

    fun rearrangeNumbers() {
        for (i in 0..9) {
        }
    }

    fun EditPassword(number: String) {
        _passwordText.value += number
    }

    fun ErasePassword() {
        if (_passwordText.value?.isEmpty() == true) return

        _passwordText.value =
            _passwordText.value?.length?.let { _passwordText.value?.substring(0, it.minus(1)) }
    }
}
