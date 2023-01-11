package com.keyneez.presentation.login.phone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneViewModel @Inject constructor() : ViewModel() {
    val phoneNumber = MutableLiveData("")
}
