package com.keyneez.presentation.login.phone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PhoneViewModel @Inject constructor() : ViewModel() {
    val phoneNumber = MutableLiveData("")
}
