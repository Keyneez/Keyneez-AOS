package com.keyneez.presentation.signup.otp.confirm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupOtpConfirmViewModel @Inject constructor() : ViewModel() {
    private val _passwordText = MutableLiveData<String>("")
    val passwordText: LiveData<String>
        get() = _passwordText

    fun resetPassword() {
        _passwordText.value = ""
    }
}
