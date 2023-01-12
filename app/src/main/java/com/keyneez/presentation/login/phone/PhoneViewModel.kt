package com.keyneez.presentation.login.phone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyneez.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    val phoneNumber = MutableLiveData("")
}
