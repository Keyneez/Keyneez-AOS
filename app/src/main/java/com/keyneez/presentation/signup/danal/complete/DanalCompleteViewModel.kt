package com.keyneez.presentation.signup.danal.complete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keyneez.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DanalCompleteViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _userName = MutableLiveData("")
    val userName: LiveData<String>
        get() = _userName

    fun initUserName() {
        Timber.tag("INIT_NAME").d("${userRepository.getUserName()}")
        _userName.value = userRepository.getUserName()
    }
}
